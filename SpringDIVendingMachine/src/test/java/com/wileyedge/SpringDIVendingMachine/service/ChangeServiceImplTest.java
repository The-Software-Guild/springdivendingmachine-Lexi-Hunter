package com.wileyedge.SpringDIVendingMachine.service;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("ChangeServiceImpl Test")
public class ChangeServiceImplTest {

	private ChangeServiceImpl changeService;

    @BeforeEach
    public void setUp() {
        changeService = new ChangeServiceImpl();
    }

    @Test
    public void testCalculateChange_forZeroAmount() {
        List<Integer> change = changeService.calculateChange(new BigDecimal(0));

        assertEquals(0, change.get(0));
        assertEquals(0, change.get(1));
        assertEquals(0, change.get(2));
        assertEquals(0, change.get(3));
    }

    @Test
    public void testCalculateChange_forValidAmount() {
        List<Integer> change = changeService.calculateChange(new BigDecimal(0.89));

        assertEquals(3, change.get(0)); // 3 quarters
        assertEquals(1, change.get(1)); // 1 dime
        assertEquals(0, change.get(2)); // 0 nickels
        assertEquals(4, change.get(3)); // 4 pennies
    }

    @Test
    public void testCalculateChange_forNegativeAmount() {
        List<Integer> change = changeService.calculateChange(new BigDecimal(-0.50));

        assertEquals(0, change.get(0));
        assertEquals(0, change.get(1));
        assertEquals(0, change.get(2));
        assertEquals(0, change.get(3));
    }
}
