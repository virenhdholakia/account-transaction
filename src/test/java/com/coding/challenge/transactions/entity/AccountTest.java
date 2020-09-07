package com.coding.challenge.transactions.entity;

import com.coding.challenge.transactions.enums.AccountTypes;
import com.coding.challenge.transactions.enums.Currency;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AccountTest {

    private Random random;

    @Before
    public void setUp() {
        random = new Random();
    }

    @After
    public void cleanUp() {
        random = null;
    }

    @Test
    public void getterTest() {

        Account account = mock(Account.class);

        String customerId = Integer.toString(random.nextInt());
        when(account.getCustomerId()).thenReturn(customerId);
        assertEquals(account.getCustomerId(), customerId);

        String accountNumber = Integer.toString(random.nextInt());
        when(account.getAccountNumber()).thenReturn(accountNumber);
        assertEquals(account.getAccountNumber(), accountNumber);

        String accountName = "Test Account";
        when(account.getAccountName()).thenReturn(accountName);
        assertEquals(account.getAccountName(), accountName);

        AccountTypes accountType = AccountTypes.values()[random.nextInt(AccountTypes.values().length)];
        when(account.getAccountType()).thenReturn(accountType);
        assertEquals(account.getAccountType(), accountType);

        Date today = new Date();
        when(account.getBalanceDate()).thenReturn(today);
        assertEquals(account.getBalanceDate(), today);

        Currency currency = Currency.values()[random.nextInt(Currency.values().length)];
        when(account.getCurrency()).thenReturn(currency);
        assertEquals(account.getCurrency(), currency);

        String balance = Double.toString(random.nextDouble());
        when(account.getBalance()).thenReturn(balance);
        assertEquals(account.getBalance(), balance);

    }

    @Test
    public void setterTest() {

        Account account = new Account();

        String customerId = Integer.toString(random.nextInt());
        account.setCustomerId(customerId);
        assertEquals(account.getCustomerId(), customerId);

        String accountNumber = Integer.toString(random.nextInt());
        account.setAccountNumber(accountNumber);
        assertEquals(account.getAccountNumber(), accountNumber);

        String accountName = "Test Account";
        account.setAccountName(accountName);
        assertEquals(account.getAccountName(), accountName);

        AccountTypes accountType = AccountTypes.values()[random.nextInt(AccountTypes.values().length)];
        account.setAccountType(accountType);
        assertEquals(account.getAccountType(), accountType);

        Date today = new Date();
        account.setBalanceDate(today);
        assertEquals(account.getBalanceDate(), today);

        Currency currency = Currency.values()[random.nextInt(Currency.values().length)];
        account.setCurrency(currency);
        assertEquals(account.getCurrency(), currency);

        String balance = Double.toString(random.nextDouble());
        account.setBalance(balance);
        assertEquals(account.getBalance(), balance);

    }

}