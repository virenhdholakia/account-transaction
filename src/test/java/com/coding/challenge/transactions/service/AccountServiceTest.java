package com.coding.challenge.transactions.service;

import com.coding.challenge.transactions.entity.Account;
import com.coding.challenge.transactions.enums.AccountTypes;
import com.coding.challenge.transactions.enums.Currency;
import com.coding.challenge.transactions.repository.AccountRepository;
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
public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

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
    public void whenAdd0Account_getCustomerAccountsTest_returns0Account(){

        String customerId = Integer.toString(random.nextInt());

        List<Account> accounts = new ArrayList<>();
        accounts.add(getAccount(customerId));

        when(accountRepository.findByCustomerId(customerId)).thenReturn(accounts);

        assertNotEquals(accountService.getCustomerAccounts("0"), accounts);
        assertTrue(accountService.getCustomerAccounts("0").size() == 0);
    }

    @Test
    public void whenAdd1Account_getCustomerAccountsTest_returns1Account(){

        String customerId = Integer.toString(random.nextInt());

        List<Account> accounts = new ArrayList<>();
        accounts.add(getAccount(customerId));

        when(accountRepository.findByCustomerId(customerId)).thenReturn(accounts);

        assertEquals(accountService.getCustomerAccounts(customerId), accounts);
        assertTrue(accountService.getCustomerAccounts(customerId).size() == 1);
    }

    @Test
    public void whenAddMultipleAccounts_getCustomerAccountsTest_returnsMultipleAccounts() {

        String customerId = Integer.toString(random.nextInt());

        List accounts = new ArrayList();
        accounts.add(getAccount(customerId));
        accounts.add(getAccount(customerId));
        accounts.add(getAccount(customerId));

        when(accountRepository.findByCustomerId(customerId)).thenReturn(accounts);

        assertEquals(accountService.getCustomerAccounts(customerId), accounts);
        assertTrue(accountService.getCustomerAccounts(customerId).size() == 3);
    }

    private Account getAccount(String customerId) {
        Account account = mock(Account.class);

        String accountNumber = Integer.toString(random.nextInt());
        String accountName = "Test Account";
        AccountTypes accountType = AccountTypes.values()[random.nextInt(AccountTypes.values().length)];
        Date today = new Date();
        Currency currency = Currency.values()[random.nextInt(Currency.values().length)];
        String balance = Double.toString(random.nextDouble());

        when(account.getCustomerId()).thenReturn(customerId);
        when(account.getAccountNumber()).thenReturn(accountNumber);
        when(account.getAccountName()).thenReturn(accountName);
        when(account.getAccountType()).thenReturn(accountType);
        when(account.getBalanceDate()).thenReturn(today);
        when(account.getCurrency()).thenReturn(currency);
        when(account.getBalance()).thenReturn(balance);

        return account;
    }
}
