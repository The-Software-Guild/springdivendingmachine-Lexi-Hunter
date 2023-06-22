package com.wileyedge.SpringDIVendingMachine.service;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.wileyedge.SpringDIVendingMachine.dao.ItemDao;
import com.wileyedge.SpringDIVendingMachine.dao.ItemDaoImpl;
import com.wileyedge.SpringDIVendingMachine.exception.ItemNotFoundException;
import com.wileyedge.SpringDIVendingMachine.exception.NoItemInventoryException;
import com.wileyedge.SpringDIVendingMachine.model.Item;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("ItemServiceImpl Test")
public class ItemServiceImplTest {

    private ItemServiceImpl itemService;
    private ItemDao itemDao;

    @BeforeEach
    public void setup() {
        System.out.println("Inside setup of ItemServiceImplTest");
        itemDao = new ItemDaoImpl();
        itemService = new ItemServiceImpl(itemDao);
    }

    @Test
    @DisplayName("getItems method test")
    public void testGetItems() {
        System.out.println("Inside testGetItems of ItemServiceImplTest");
        List<Item> items = itemService.getItems();
        assertNotNull(items, "Items should not be null");
    }

    @Test
    @DisplayName("getItemByName method test")
    public void testGetItemByName() {
        System.out.println("Inside testGetItemByName of ItemServiceImplTest");

        String itemName = "Twix";
        Item item = itemService.getItemByName(itemName);

        assertNotNull(item, "Item should not be null");
        assertEquals(itemName, item.getName(), "Item name should match the actual item name");
    }

    @Test
    @DisplayName("updateItem method test")
    public void testUpdateItem() {
        System.out.println("Inside testUpdateItem of ItemServiceImplTest");

        int itemId = 1;
        Item updatedItem = new Item(itemId, "UpdatedItem", new BigDecimal("2.50"), 5);

        try {
            itemService.updateItem(itemId, updatedItem);
        } catch (ItemNotFoundException e) {
            fail("Item update should not throw an exception");
        }

        Item itemFromService = itemService.getItemById(itemId);

        assertNotNull(itemFromService, "Item should not be null");
        assertEquals(updatedItem.getName(), itemFromService.getName(), "Item name should match the updated item name");
    }
    
    @Test
    void testIsValidItemChoice_validItem() {
        int validItemId = 1;
        try {
			assertTrue(itemService.isValidItemChoice(validItemId), "Should return true for valid item choice with sufficient inventory.");
		} catch (NoItemInventoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Test
    void testIsValidItemChoice_invalidItem() {
        int invalidItemId = 999;
        try {
			assertFalse(itemService.isValidItemChoice(invalidItemId), "Should return false for invalid item choice.");
		} catch (NoItemInventoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Test
    void testIsValidItemChoice_noInventory() {
        int itemIdWithNoInventory = 0;
        try {
			assertFalse(itemService.isValidItemChoice(itemIdWithNoInventory), "Should return false for valid item choice but with zero inventory.");
		} catch (NoItemInventoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
