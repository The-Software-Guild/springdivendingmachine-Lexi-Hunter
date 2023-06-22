package com.wileyedge.SpringDIVendingMachine.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wileyedge.SpringDIVendingMachine.dao.ItemDao;
import com.wileyedge.SpringDIVendingMachine.exception.InsufficientFundsException;
import com.wileyedge.SpringDIVendingMachine.exception.ItemNotFoundException;
import com.wileyedge.SpringDIVendingMachine.exception.NoItemInventoryException;
import com.wileyedge.SpringDIVendingMachine.exception.VendingMachineItemsFileNotFoundException;
import com.wileyedge.SpringDIVendingMachine.exception.VendingMachinePersistenceException;
import com.wileyedge.SpringDIVendingMachine.model.Item;

@Service
public class ItemServiceImpl implements ItemService {

	ItemDao itemDao;
	
	public ItemServiceImpl() {
		
	}
	
	@Autowired
	public ItemServiceImpl(ItemDao itemDao) {
		this.itemDao = itemDao;
	}
	
	@Override
	public List<Item> getItems() {
		return itemDao.getItems();
	}

	@Override
	public Item getItemByName(String name) {
		return itemDao.getItemByName(name);
	}

	@Override
	public Item getItemById(int id) {
		return itemDao.getItemById(id);
	}

	@Override
	public void updateItem(int id, Item item) throws ItemNotFoundException {
		itemDao.updateItem(id, item);
	}

	@Override
	public void readItemsFile() throws VendingMachineItemsFileNotFoundException {
		itemDao.readItemsFile();

	}
	
	@Override
	public boolean isValidItemChoice(int itemId) throws NoItemInventoryException {
        Item item = itemDao.getItemById(itemId);
        
        if (item == null) {
            System.out.println("Invalid item ID. Please try again with a valid ID.");
            return false;
        }

        if (item.getInventory() <= 0) {
        	throw new NoItemInventoryException("Sorry, the item with ID " + itemId + " is currently out of stock.");
        }

        return true;
    }
	
	@Override
	public boolean sufficientFunds(BigDecimal userFunds, int itemId) throws InsufficientFundsException {
	    Item item = itemDao.getItemById(itemId);

	    if (item != null && userFunds.compareTo(item.getCost()) < 0) {
	        throw new InsufficientFundsException("Insufficient funds for this item.");
	    }
	    
	    return true;
	}
	
	@Override
	public void saveItemsToFile() throws VendingMachinePersistenceException{
		itemDao.saveItemsToFile();
	}

}







