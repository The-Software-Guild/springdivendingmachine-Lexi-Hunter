package com.wileyedge.SpringDIVendingMachine.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.wileyedge.SpringDIVendingMachine.dao.AuditDao;
import com.wileyedge.SpringDIVendingMachine.exception.AuditDaoException;
import com.wileyedge.SpringDIVendingMachine.exception.InsufficientFundsException;
import com.wileyedge.SpringDIVendingMachine.exception.ItemNotFoundException;
import com.wileyedge.SpringDIVendingMachine.exception.NoItemInventoryException;
import com.wileyedge.SpringDIVendingMachine.exception.VendingMachinePersistenceException;
import com.wileyedge.SpringDIVendingMachine.model.Item;
import com.wileyedge.SpringDIVendingMachine.service.ChangeService;
import com.wileyedge.SpringDIVendingMachine.service.ItemService;
import com.wileyedge.SpringDIVendingMachine.view.VendingMachineView;

@Controller
public class VendingMachineController {

    @Autowired
    ItemService itemService;

    @Autowired
    VendingMachineView view;

    @Autowired
    ChangeService changeService;

    @Autowired
    AuditDao auditDao;

    BigDecimal insertedMoney = new BigDecimal(0);

    public void run() {
        boolean isRunning = true;

        while (isRunning) {
            List<Item> items = itemService.getItems();
            view.displayInventory(items);
            view.displayInsertedMoney(insertedMoney);

            int action = view.promptForAction();

            switch (action) {
                case 1:
                    // Insert Money
                    BigDecimal moneyToInsert = view.promptForMoneyInsertion();
                    insertedMoney = insertedMoney.add(moneyToInsert);
                    view.displayInsertedMoney(insertedMoney);

                    // Add to Audit Log
                    try {
                        auditDao.writeAuditEntry("Inserted Money: " + moneyToInsert);
                    } catch (AuditDaoException e) {
                        System.out.println("Error: Could not write to audit log.");
                    }
                    break;

                case 2:
                    // Dispense item
                    if (insertedMoney.compareTo(new BigDecimal(0)) == 0) {
                        System.out.println("Input money before selecting an item!");
                        break;
                    }

                    int itemId = view.promptForItemChoice();
				try {
					if (itemService.isValidItemChoice(itemId)) {
                        try {
                            if (itemService.sufficientFunds(insertedMoney, itemId)) {
                                Item chosenItem = itemService.getItemById(itemId);
                                itemService.updateItem(itemId, new Item(itemId, chosenItem.getName(), chosenItem.getCost(), chosenItem.getInventory() - 1));
                                
                                // Add to Audit Log
                                auditDao.writeAuditEntry("Dispensed Item: " + chosenItem.getName());

                                insertedMoney = insertedMoney.subtract(chosenItem.getCost());
                                view.printChange(changeService.calculateChange(insertedMoney));
                                
                                itemService.updateItem(itemId, new Item(itemId, chosenItem.getName(), chosenItem.getCost(), chosenItem.getInventory() - 1));
                                
                                // Add to Audit Log
                                auditDao.writeAuditEntry("Returned change: " + insertedMoney);

                                insertedMoney = new BigDecimal(0);
                            }
                        } catch (ItemNotFoundException | AuditDaoException | InsufficientFundsException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Invalid item choice.");
                    }
				} catch (NoItemInventoryException e) {
					System.out.println(e.getMessage());
				}
                    break;

                case 3:
                    System.out.println("Thank you for using the vending machine, see you soon!");
                    try {
                        itemService.saveItemsToFile();

                        // Add to Audit Log
                        auditDao.writeAuditEntry("Saving Items to File");

                    } catch (VendingMachinePersistenceException | AuditDaoException e) {
                        System.out.println(e.getMessage());
                    }
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid action.");
            }
        }
    }

}
