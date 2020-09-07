package com.coding.challenge.transactions.enums;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class AccountTypesTest {

    @Test
    public void savingsAccountTypesEnumTest() {
        AccountTypes accountTypes = AccountTypes.SAVINGS;
        String accountTypesSavings = "Savings";
        assertEquals(accountTypes, AccountTypes.SAVINGS);
        assertEquals(accountTypesSavings, AccountTypes.SAVINGS.getAccountType());
    }

    @Test
    public void currentAccountTypesEnumTest() {
        AccountTypes accountTypes = AccountTypes.CURRENT;
        String accountTypesCurrent = "Current";
        assertEquals(accountTypes, AccountTypes.CURRENT);
        assertEquals(accountTypesCurrent, AccountTypes.CURRENT.getAccountType());
    }

    @Test
    public void crossAccountTypesEnumTest() {
        AccountTypes accountTypes = AccountTypes.CURRENT;
        String accountTypesCurrent = "Current";
        assertFalse(accountTypes == AccountTypes.SAVINGS);
        assertFalse(accountTypesCurrent == AccountTypes.SAVINGS.getAccountType());

        accountTypes = AccountTypes.SAVINGS;
        String accountTypesSavings = "Savings";
        assertFalse(accountTypes == AccountTypes.CURRENT);
        assertFalse(accountTypesSavings == AccountTypes.CURRENT.getAccountType());
    }
}
