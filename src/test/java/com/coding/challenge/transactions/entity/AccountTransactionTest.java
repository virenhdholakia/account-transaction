package com.coding.challenge.transactions.entity;

import com.coding.challenge.transactions.enums.Currency;
import com.coding.challenge.transactions.enums.TransactionType;
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
public class AccountTransactionTest {

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

        AccountTransaction accountTransaction = mock(AccountTransaction.class);

        String accountNumber = Integer.toString(random.nextInt());
        when(accountTransaction.getAccountNumber()).thenReturn(accountNumber);
        assertEquals(accountTransaction.getAccountNumber(), accountNumber);

        Date transactionDate = new Date();
        when(accountTransaction.getTransactionDate()).thenReturn(transactionDate);
        assertEquals(accountTransaction.getTransactionDate(), transactionDate);

        Currency transactionCurrency = Currency.values()[random.nextInt(Currency.values().length)];
        when(accountTransaction.getTransactionCurrency()).thenReturn(transactionCurrency);
        assertEquals(accountTransaction.getTransactionCurrency(), transactionCurrency);

        String amount = Double.toString(random.nextDouble());
        when(accountTransaction.getAmount()).thenReturn(amount);
        assertEquals(accountTransaction.getAmount(), amount);

        TransactionType transactionType = TransactionType.values()[random.nextInt(TransactionType.values().length)];
        when(accountTransaction.getTransactionType()).thenReturn(transactionType);
        assertEquals(accountTransaction.getTransactionType(), transactionType);

        String transactionNarrative = "Test Description";
        when(accountTransaction.getTransactionNarrative()).thenReturn(transactionNarrative);
        assertEquals(accountTransaction.getTransactionNarrative(), transactionNarrative);

    }

    @Test
    public void setterTest() {

        AccountTransaction accountTransaction = new AccountTransaction();

        String accountNumber = Integer.toString(random.nextInt());
        accountTransaction.setAccountNumber(accountNumber);
        assertEquals(accountTransaction.getAccountNumber(), accountNumber);

        Date transactionDate = new Date();
        accountTransaction.setTransactionDate(transactionDate);
        assertEquals(accountTransaction.getTransactionDate(), transactionDate);

        Currency transactionCurrency = Currency.values()[random.nextInt(Currency.values().length)];
        accountTransaction.setTransactionCurrency(transactionCurrency);
        assertEquals(accountTransaction.getTransactionCurrency(), transactionCurrency);

        String amount = Double.toString(random.nextDouble());
        accountTransaction.setAmount(amount);
        assertEquals(accountTransaction.getAmount(), amount);

        TransactionType transactionType = TransactionType.values()[random.nextInt(TransactionType.values().length)];
        accountTransaction.setTransactionType(transactionType);
        assertEquals(accountTransaction.getTransactionType(), transactionType);

        String transactionNarrative = "Test Description";
        accountTransaction.setTransactionNarrative(transactionNarrative);
        assertEquals(accountTransaction.getTransactionNarrative(), transactionNarrative);

    }
}
