package com.wileyedge.SpringDIVendingMachine.view;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.wileyedge.SpringDIVendingMachine.model.Item;

@Component
public class VendingMachineViewImpl implements VendingMachineView {

	private Scanner scanner = new Scanner(System.in);
	
	@Override
	public void displayInventory(List<Item> items) {
	    System.out.println("\nCurrent Inventory:");
	    System.out.println("--------------------------------------------------------");
	    System.out.println(String.format("%-5s | %-20s | %-5s | %-10s", "ID", "Name", "Cost", "Inventory"));
	    System.out.println("--------------------------------------------------------");

	    for (Item item : items) {
	    	if(item.getInventory() > 0) {
		        System.out.println(String.format("%-5d | %-20s | %-5s | %-10d", 
		            item.getId(), item.getName(), item.getCost(), item.getInventory()));
	    	}
	    }

	    System.out.println("--------------------------------------------------------\n");
	}
	
	@Override
	public void displayInsertedMoney(BigDecimal insertedMoney) {
	    System.out.println("Inserted Money:");
	    System.out.println("--------------------------------------------------------");
	    System.out.println(String.format("%-5s", insertedMoney.setScale(2, RoundingMode.HALF_UP)));
	    System.out.println("--------------------------------------------------------");
	}
	
	@Override
	public BigDecimal promptForMoneyInsertion() {
        BigDecimal insertedMoney = null;
        while (insertedMoney == null) {
            try {
                System.out.println("\nPlease insert your money:");
                String input = scanner.nextLine();
                insertedMoney = new BigDecimal(input);
                if (insertedMoney.compareTo(BigDecimal.ZERO) < 0) {
                    System.out.println("Please enter a positive amount of money.");
                    insertedMoney = null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid amount of money.");
            }
        }
        return insertedMoney;
    }
	
	@Override
	public int promptForItemChoice() {
        Integer itemId = null;
        while (itemId == null) {
            try {
                System.out.println("\nPlease enter the ID of the item you wish to purchase:");
                String input = scanner.nextLine();
                itemId = Integer.parseInt(input);
                if (itemId < 0) {
                    System.out.println("Please enter a valid item ID.");
                    itemId = null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid item ID.");
            }
        }
        return itemId;
    }
	
	@Override
	public int promptForAction() {
        Integer choice = null;
        while (choice == null) {
            try {
                System.out.println("\nPlease choose an action:");
                System.out.println("1. Insert money");
                System.out.println("2. Choose item to dispense");
                System.out.println("3. Exit");
                String input = scanner.nextLine();
                choice = Integer.parseInt(input);
                if (choice < 1 || choice > 3) {
                    System.out.println("Invalid choice. Please enter 1, 2 or 3.");
                    choice = null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return choice;
    }
	
	@Override
	public void printChange(List<Integer> coinAmounts) {
	    System.out.println("\nHere's your change:");

	    if (coinAmounts.get(0) > 0) {
	        System.out.println(coinAmounts.get(0) + (coinAmounts.get(0) > 1 ? " quarters" : " quarter"));
	    }

	    if (coinAmounts.get(1) > 0) {
	        System.out.println(coinAmounts.get(1) + (coinAmounts.get(1) > 1 ? " dimes" : " dime"));
	    }

	    if (coinAmounts.get(2) > 0) {
	        System.out.println(coinAmounts.get(2) + (coinAmounts.get(2) > 1 ? " nickels" : " nickel"));
	    }

	    if (coinAmounts.get(3) > 0) {
	        System.out.println(coinAmounts.get(3) + (coinAmounts.get(3) > 1 ? " pennies" : " penny"));
	    }

	}

}
