package com.wileyedge.SpringDIVendingMachine.dao;

import java.util.List;

import com.wileyedge.SpringDIVendingMachine.exception.ItemNotFoundException;
import com.wileyedge.SpringDIVendingMachine.exception.VendingMachineItemsFileNotFoundException;
import com.wileyedge.SpringDIVendingMachine.exception.VendingMachinePersistenceException;
import com.wileyedge.SpringDIVendingMachine.model.Item;

public interface ItemDao {
	List<Item> getItems();
    Item getItemByName(String name);
    Item getItemById(int id);
    void updateItem(int id, Item item) throws ItemNotFoundException;
	void readItemsFile() throws VendingMachineItemsFileNotFoundException;
	void saveItemsToFile() throws VendingMachinePersistenceException;
}
