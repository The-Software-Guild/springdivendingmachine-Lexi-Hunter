package com.wileyedge.SpringDIVendingMachine.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.wileyedge.SpringDIVendingMachine.exception.AuditDaoException;

@Component
public class AuditDaoImpl implements AuditDao {

	public static final String AUDIT_FILE = "C:\\C353\\VendingMachine\\Data\\AuditLog.txt";

    @Override
    public void writeAuditEntry(String entry) throws AuditDaoException {
        PrintWriter out;

        try {
            // Set FileWriter to append
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new AuditDaoException("Could not persist audit information.");
        }

        // Write the current date, time, and entry to the audit log
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
        out.close();
    }

}
