package com.coding.challenge.transactions.controller;

import com.coding.challenge.transactions.entity.AccountTransaction;
import com.coding.challenge.transactions.service.AccountTransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountTransactionControllerTest {

    @Autowired
    private AccountTransactionController accountTransactionController;

    @MockBean
    private AccountTransactionService accountTransactionService;

    @Mock
    private AccountTransaction accountTransaction;

    @Test(expected = Exception.class)
    public void whenNoAccountNumber_getAccountTransactionsTest_throwsException() throws Exception {

        accountTransactionController.getAccountTransactions(null);
    }

    @Test(expected = Exception.class)
    public void whenAccountNumber0_getAccountTransactionsTest_throwsException() throws Exception {

        accountTransactionController.getAccountTransactions("0");
    }

    @Test
    public void whenAccountNumberPresent_getAccountTransactionsTest_returnsList() throws Exception {

        List<AccountTransaction> transactions = new ArrayList<>();
        transactions.add(accountTransaction);

        ResponseEntity<List<AccountTransaction>> responseEntity = new ResponseEntity<>(transactions, OK);
        when(accountTransactionService.getUserAccountTransactions("12345")).thenReturn(transactions);

        assertEquals(accountTransactionController.getAccountTransactions("12345"), responseEntity);
        assertTrue(accountTransactionController.getAccountTransactions("12345").getBody().size() == 1);

        AccountTransaction accountTransaction1 = mock(AccountTransaction.class);
        AccountTransaction accountTransaction2 = mock(AccountTransaction.class);

        transactions.add(accountTransaction1);
        transactions.add(accountTransaction2);

        assertEquals(accountTransactionController.getAccountTransactions("12345"), responseEntity);
        assertTrue(accountTransactionController.getAccountTransactions("12345").getBody().size() == 3);

    }

}
