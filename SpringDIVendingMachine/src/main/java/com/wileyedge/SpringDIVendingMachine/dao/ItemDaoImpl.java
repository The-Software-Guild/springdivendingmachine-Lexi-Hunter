package com.wileyedge.SpringDIVendingMachine.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.wileyedge.SpringDIVendingMachine.exception.ItemNotFoundException;
import com.wileyedge.SpringDIVendingMachine.exception.VendingMachineItemsFileNotFoundException;
import com.wileyedge.SpringDIVendingMachine.exception.VendingMachinePersistenceException;
import com.wileyedge.SpringDIVendingMachine.model.Item;

@Component
public class ItemDaoImpl implements ItemDao{

	private static final String ITEMS_FILE = "C:\\C353\\VendingMachine\\Data\\Items.txt";
    private List<Item> items = new ArrayList<>();
	
    public ItemDaoImpl() {
    	try {
			readItemsFile();
		} catch (VendingMachineItemsFileNotFoundException e) {
			System.out.println(e.getMessage());
		}
    }
    
    @Override
    public void readItemsFile() throws VendingMachineItemsFileNotFoundException{
        try (BufferedReader reader = new BufferedReader(new FileReader(ITEMS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                	int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    BigDecimal cost = new BigDecimal(parts[2].trim());
                    int count = Integer.parseInt(parts[3].trim());

                    Item item = new Item();
                    item.setId(id);
                    item.setName(name);
                    item.setCost(cost);
                    item.setInventory(count);

                    items.add(item);
                }
            }
        } catch (IOException e) {
        	throw new VendingMachineItemsFileNotFoundException("Items file not found!");
        }
    }
    
    @Override
    public void saveItemsToFile() throws VendingMachinePersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter("C:\\C353\\VendingMachine\\Data\\Items.txt"));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException("Could not save item data.");
        }

        for (Item currentItem : items) {
            // write the Item object to the file
            out.println(currentItem.getId() + ","
                    + currentItem.getName() + ","
                    + currentItem.getCost() + ","
                    + currentItem.getInventory());

            out.flush();
        }
        out.close();
    }
    
	@Override
	public List<Item> getItems() {
		return items;
	}

	@Override
	public Item getItemByName(String name) {
		return items.stream().filter(i -> i.getName().equals(name)).findFirst().orElse(null);
	}
	
	@Override
	public Item getItemById(int id) {
		return items.stream().filter(i -> i.getId() == id).findFirst().orElse(null);
	}

	@Override
	public void updateItem(int id, Item item) throws ItemNotFoundException {
		Item foundItem = getItemById(id);
		
		if(foundItem != null) {
			foundItem.setName(item.getName());
			foundItem.setCost(item.getCost());
			foundItem.setInventory(item.getInventory());
		}else {
			throw new ItemNotFoundException("Item with id: " + id + " not found!");
		}
	}

}
