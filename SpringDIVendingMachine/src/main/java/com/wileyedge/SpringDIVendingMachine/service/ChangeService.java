package com.wileyedge.SpringDIVendingMachine.service;

import java.math.BigDecimal;
import java.util.List;

public interface ChangeService {
	public List<Integer> calculateChange(BigDecimal changeAmount);
}
