package com.wileyedge.SpringDIVendingMachine.exception;

public class NoItemInventoryException extends Exception {

	public NoItemInventoryException(String message) {
        super(message);
    }
}
