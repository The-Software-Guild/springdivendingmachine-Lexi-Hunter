package com.wileyedge.SpringDIVendingMachine.view;

import java.math.BigDecimal;
import java.util.List;

import com.wileyedge.SpringDIVendingMachine.model.Item;

public interface VendingMachineView {

	void displayInventory(List<Item> items);
	void displayInsertedMoney(BigDecimal insertedMoney);
	BigDecimal promptForMoneyInsertion();
	int promptForItemChoice();
	int promptForAction();
	void printChange(List<Integer> coinAmounts);
}
