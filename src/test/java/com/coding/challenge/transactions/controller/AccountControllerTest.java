package com.coding.challenge.transactions.controller;

import com.coding.challenge.transactions.beans.Customer;
import com.coding.challenge.transactions.entity.Account;
import com.coding.challenge.transactions.service.AccountService;
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
public class AccountControllerTest {

    @Autowired
    private AccountController accountController;

    @MockBean
    private AccountService accountService;

    @Mock
    private Account account;

    @Mock
    private Customer customer;

    @Test(expected = Exception.class)
    public void whenNoCustomer_getUserAccountsTest_throwsCustomerNotFoundException() throws Exception {

        accountController.getUserAccounts(null);
    }

    @Test(expected = Exception.class)
    public void whenNoCustomerId_getUserAccountsTest_throwsCustomerNotFoundException() throws Exception {

        accountController.getUserAccounts(customer);
    }

    @Test
    public void whenCustomerIdPresent_getUserAccountsTest_returnsList() throws Exception {

        List<Account> accounts = new ArrayList<>();
        accounts.add(account);

        when(customer.getCustomerId()).thenReturn("12345");

        ResponseEntity<List<Account>> responseEntity =new ResponseEntity<>(accounts, OK);

        when(accountService.getCustomerAccounts(customer.getCustomerId())).thenReturn(accounts);

        assertEquals(accountController.getUserAccounts(customer), responseEntity);
        assertTrue(accountController.getUserAccounts(customer).getBody().size() == 1);

        Account account1 = mock(Account.class);
        Account account2 = mock(Account.class);

        accounts.add(account1);
        accounts.add(account2);

        assertEquals(accountController.getUserAccounts(customer), responseEntity);
        assertTrue(accountController.getUserAccounts(customer).getBody().size() == 3);
    }
}
