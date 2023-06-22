package com.wileyedge.SpringDIVendingMachine.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ChangeServiceImpl implements ChangeService{

	public List<Integer> calculateChange(BigDecimal changeAmount) {
		
		if(changeAmount.compareTo(new BigDecimal(0)) <= 0) {
			List<Integer> changeList = new ArrayList<>();
	        changeList.add(0);
	        changeList.add(0);
	        changeList.add(0);
	        changeList.add(0);
	        return changeList;
		}
        BigDecimal hundred = new BigDecimal(100);
        
        int change = changeAmount.multiply(hundred).intValue();

        int quarters = change / 25;
        change %= 25;

        int dimes = change / 10;
        change %= 10;

        int nickels = change / 5;
        change %= 5;

        int pennies = change;
        
        List<Integer> changeList = new ArrayList<>();
        changeList.add(quarters);
        changeList.add(dimes);
        changeList.add(nickels);
        changeList.add(pennies);
        return changeList;
        
    }
}
