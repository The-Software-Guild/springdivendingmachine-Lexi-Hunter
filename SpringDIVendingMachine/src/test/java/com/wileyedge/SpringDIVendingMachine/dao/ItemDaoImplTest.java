package com.wileyedge.SpringDIVendingMachine.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.wileyedge.SpringDIVendingMachine.exception.ItemNotFoundException;
import com.wileyedge.SpringDIVendingMachine.exception.VendingMachineItemsFileNotFoundException;
import com.wileyedge.SpringDIVendingMachine.model.Item;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("ItemDaoImpl Test")
public class ItemDaoImplTest {

	private ItemDaoImpl itemDao;
	
	@BeforeEach
    public void setup() {
        System.out.println("Inside setup of ItemDaoImplTest");
        itemDao = new ItemDaoImpl();
    }
	
	@Test
	@DisplayName("getItems method test")
    public void testGetItems() {
        System.out.println("Inside testGetItems of ItemDaoImplTest");
        
        int actualResult = itemDao.getItems().size();
        System.out.println(actualResult);
        for(Item i : itemDao.getItems()) {
        	System.out.println(i);
        }
        int expectedResult = 18;
        assertEquals(expectedResult, actualResult, "The total number of items should be 18.");
    }
	
	@Test
	@DisplayName("getItem method test")
    public void testGetItem() {
        System.out.println("Inside testGetItem of ItemDaoImplTest");

        String itemName = "Doritos";
        Item actualResult = itemDao.getItemByName(itemName);

        assertNotNull(actualResult, "An item with name " + itemName + " should exist.");
        
        itemName = "Skittles";
        actualResult = itemDao.getItemByName(itemName);

        assertNotNull(actualResult, "An item with name " + itemName + " should exist.");
    }
	
	@Test
	@DisplayName("updateItem method test")
    public void testUpdateItem() {
        System.out.println("Inside testUpdateItem of ItemDaoImplTest");

        Item updatedItem = new Item(2, "Banta", new BigDecimal(1.75), 7);
        
        try {
			itemDao.updateItem(2, updatedItem);
		} catch (ItemNotFoundException e) {
			e.printStackTrace();
		}
        
        Item returnedUpdatedItem = itemDao.getItemById(2);
        
        System.out.println();
        
        assertEquals(updatedItem.getName(), returnedUpdatedItem.getName(), "The updated name should be Banta");
    }
}







