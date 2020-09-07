package com.coding.challenge.transactions.controller;

import com.coding.challenge.transactions.Application;
import com.coding.challenge.transactions.entity.AccountTransaction;
import com.coding.challenge.transactions.enums.Currency;
import com.coding.challenge.transactions.enums.TransactionType;
import com.coding.challenge.transactions.repository.AccountTransactionsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = Application.class
)
@AutoConfigureMockMvc
public class AccountTransactionControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AccountTransactionsRepository accountTransactionsRepository;

    @Test
    public void whenNoAccountNumberPresent_getAccountTransactionsTest_returnsNOTFOUNDStatus() throws Exception {

        String accountNumber = "12345";
        getAccountTransaction(accountNumber);
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/transactions/getAccountTransactions/0")
                        .accept(APPLICATION_JSON_VALUE)
        ).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertThat(response.getStatus()).isEqualTo(NOT_FOUND.value());

    }

    @Test
    public void whenNoTransactionsForAccountNumberPresent_getAccountTransactionsTest_returnsNOTFOUNDStatus() throws Exception {

        String accountNumber = "12345";
        getAccountTransaction(accountNumber);
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/transactions/getAccountTransactions/54321")
                        .accept(APPLICATION_JSON_VALUE)
        ).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertThat(response.getStatus()).isEqualTo(NOT_FOUND.value());

    }

    @Test
    public void whenAccountNumberPresent_getAccountTransactionsTest_returnsOKStatus() throws Exception {

        String accountNumber = "12345";
        getAccountTransaction(accountNumber);
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/transactions/getAccountTransactions/"+accountNumber)
                        .accept(APPLICATION_JSON_VALUE)
        ).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertThat(response.getStatus()).isEqualTo(OK.value());

    }

    private AccountTransaction getAccountTransaction(String accountNumber) {
        Random random = new Random();

        AccountTransaction accountTransaction = new AccountTransaction();

        accountTransaction.setAccountNumber(accountNumber);
        accountTransaction.setTransactionNarrative("Test Transaction");
        accountTransaction.setTransactionType(TransactionType.values()[random.nextInt(TransactionType.values().length)]);
        accountTransaction.setTransactionCurrency(Currency.values()[random.nextInt(Currency.values().length)]);
        accountTransaction.setTransactionDate(new Date());
        accountTransaction.setAmount(Double.toString(random.nextDouble()));

        accountTransactionsRepository.save(accountTransaction);

        return accountTransaction;
    }
}
