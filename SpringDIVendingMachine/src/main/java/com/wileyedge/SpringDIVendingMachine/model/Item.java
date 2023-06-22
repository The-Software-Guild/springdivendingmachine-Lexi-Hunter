package com.wileyedge.SpringDIVendingMachine.model;

import java.math.BigDecimal;

public class Item {

	private int id;
	private String name;
    private BigDecimal cost;
    private int inventory;
    
    public Item() {
    	
    }
    
	public Item(int id, String name, BigDecimal cost, int inventory) {
		super();
		this.id = id;
		this.name = name;
		this.cost = cost;
		this.inventory = inventory;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	public int getInventory() {
		return inventory;
	}
	public void setInventory(int inventory) {
		this.inventory = inventory;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", cost=" + cost + ", inventory=" + inventory + "]";
	}

}
