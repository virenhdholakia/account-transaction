package com.coding.challenge.transactions.service;

import com.coding.challenge.transactions.entity.AccountTransaction;
import com.coding.challenge.transactions.enums.Currency;
import com.coding.challenge.transactions.enums.TransactionType;
import com.coding.challenge.transactions.repository.AccountTransactionsRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AccountTransactionServiceTest {

    @InjectMocks
    private AccountTransactionService accountTransactionService;

    @Mock
    private AccountTransactionsRepository accountTransactionsRepository;

    @Mock
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
    public void whenAdd0Transaction_getUserAccountTransactionsTest_returns0Transaction() {
        String accountNumber = Integer.toString(random.nextInt());

        List<AccountTransaction> transactions = new ArrayList<>();
        transactions.add(getAccountTransactions(accountNumber));

        when(accountTransactionsRepository.findByAccountNumber(accountNumber)).thenReturn(transactions);

        assertNotEquals(accountTransactionService.getUserAccountTransactions("0"), transactions);
        assertTrue(accountTransactionService.getUserAccountTransactions("0").size() == 0);
    }

    @Test
    public void whenAdd1Transaction_getUserAccountTransactionsTest_returns1Transaction() {
        String accountNumber = Integer.toString(random.nextInt());

        List<AccountTransaction> transactions = new ArrayList<>();
        transactions.add(getAccountTransactions(accountNumber));

        when(accountTransactionsRepository.findByAccountNumber(accountNumber)).thenReturn(transactions);

        assertEquals(accountTransactionService.getUserAccountTransactions(accountNumber), transactions);
        assertTrue(accountTransactionService.getUserAccountTransactions(accountNumber).size() == 1);
    }

    @Test
    public void whenAddMultipleTransaction_getUserAccountTransactionsTest_returnsMultipleTransaction() {
        String accountNumber = Integer.toString(random.nextInt());

        List<AccountTransaction> transactions = new ArrayList<>();
        transactions.add(getAccountTransactions(accountNumber));
        transactions.add(getAccountTransactions(accountNumber));
        transactions.add(getAccountTransactions(accountNumber));

        when(accountTransactionsRepository.findByAccountNumber(accountNumber)).thenReturn(transactions);

        assertEquals(accountTransactionService.getUserAccountTransactions(accountNumber), transactions);
        assertTrue(accountTransactionService.getUserAccountTransactions(accountNumber).size() == 3);
    }

    private AccountTransaction getAccountTransactions(String accountNumber) {
        AccountTransaction accountTransaction = mock(AccountTransaction.class);

        String amount = Double.toString(random.nextDouble());
        Currency currency = Currency.values()[random.nextInt(Currency.values().length)];
        Date today = new Date();
        String transactionNarrative = "Comment";
        TransactionType transactionType = TransactionType.values()[random.nextInt(TransactionType.values().length)];

        when(accountTransaction.getAccountNumber()).thenReturn(accountNumber);
        when(accountTransaction.getAmount()).thenReturn(amount);
        when(accountTransaction.getTransactionCurrency()).thenReturn(currency);
        when(accountTransaction.getTransactionDate()).thenReturn(today);
        when(accountTransaction.getTransactionNarrative()).thenReturn(transactionNarrative);
        when(accountTransaction.getTransactionType()).thenReturn(transactionType);

        return accountTransaction;
    }
}
