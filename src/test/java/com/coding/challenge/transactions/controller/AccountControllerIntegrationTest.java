package com.coding.challenge.transactions.controller;

import com.coding.challenge.transactions.Application;
import com.coding.challenge.transactions.beans.Customer;
import com.coding.challenge.transactions.entity.Account;
import com.coding.challenge.transactions.enums.AccountTypes;
import com.coding.challenge.transactions.enums.Currency;
import com.coding.challenge.transactions.repository.AccountRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class AccountControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void whenNoCustomerIdPresent_getUserAccountsTest_returnsNOTFOUNDStatus() throws Exception {
        String customerId = "12345";
        getAccount(customerId);

        Customer customer = new Customer();
        customer.setCustomerId("");
        customer.setCustomerName("Test Name");

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/accounts/getCustomerAccounts")
                        .accept(APPLICATION_JSON_VALUE).content(new ObjectMapper().writeValueAsString(customer)).contentType(APPLICATION_JSON_VALUE)
        ).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertThat(response.getStatus()).isEqualTo(NOT_FOUND.value());

    }

    @Test
    public void whenNoAccountsForCustomerIdPresent_getUserAccountsTest_returnsNOTFOUNDStatus() throws Exception {
        String customerId = "12345";
        getAccount(customerId);

        Customer customer = new Customer();
        customer.setCustomerId("54321");
        customer.setCustomerName("Test Name");

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/accounts/getCustomerAccounts")
                        .accept(APPLICATION_JSON_VALUE).content(new ObjectMapper().writeValueAsString(customer)).contentType(APPLICATION_JSON_VALUE)
        ).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertThat(response.getStatus()).isEqualTo(NOT_FOUND.value());

    }

    @Test
    public void whenCustomerPresent_getUserAccountsTest_returnsOKStatus() throws Exception {
        String customerId = "12345";
        getAccount(customerId);

        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setCustomerName("Test Name");

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/accounts/getCustomerAccounts")
                .accept(APPLICATION_JSON_VALUE).content(new ObjectMapper().writeValueAsString(customer)).contentType(APPLICATION_JSON_VALUE)
        ).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertThat(response.getStatus()).isEqualTo(OK.value());

    }

    private Account getAccount(String customerId) {
        Account account = new Account();
        Random random = new Random();

        account.setCustomerId(customerId);
        account.setAccountNumber(Integer.toString(random.nextInt()));
        account.setBalance(Double.toString(random.nextDouble()));
        account.setCurrency(Currency.values()[random.nextInt(Currency.values().length)]);
        account.setAccountType(AccountTypes.values()[random.nextInt(AccountTypes.values().length)]);
        account.setAccountName("Test");
        account.setBalanceDate(new Date());

        accountRepository.save(account);

        return account;
    }
}
