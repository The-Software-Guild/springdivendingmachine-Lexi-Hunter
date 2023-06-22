package com.wileyedge.SpringDIVendingMachine.service;

import java.math.BigDecimal;
import java.util.List;

import com.wileyedge.SpringDIVendingMachine.exception.InsufficientFundsException;
import com.wileyedge.SpringDIVendingMachine.exception.ItemNotFoundException;
import com.wileyedge.SpringDIVendingMachine.exception.NoItemInventoryException;
import com.wileyedge.SpringDIVendingMachine.exception.VendingMachineItemsFileNotFoundException;
import com.wileyedge.SpringDIVendingMachine.exception.VendingMachinePersistenceException;
import com.wileyedge.SpringDIVendingMachine.model.Item;

public interface ItemService {

	List<Item> getItems();
    Item getItemByName(String name);
    Item getItemById(int id);
    void updateItem(int id, Item item) throws ItemNotFoundException;
	void readItemsFile() throws VendingMachineItemsFileNotFoundException;
	boolean isValidItemChoice(int itemId) throws NoItemInventoryException;
	void saveItemsToFile() throws VendingMachinePersistenceException;
	boolean sufficientFunds(BigDecimal userFunds, int itemId) throws InsufficientFundsException;
}
