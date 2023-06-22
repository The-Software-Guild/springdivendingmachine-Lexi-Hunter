package com.wileyedge.SpringDIVendingMachine.dao;

import com.wileyedge.SpringDIVendingMachine.exception.AuditDaoException;

public interface AuditDao {

	void writeAuditEntry(String entry) throws AuditDaoException;
}
