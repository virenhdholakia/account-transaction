package com.coding.challenge.transactions.controller;

import com.coding.challenge.transactions.beans.Customer;
import com.coding.challenge.transactions.entity.Account;
import com.coding.challenge.transactions.exception.CustomerNotFoundException;
import com.coding.challenge.transactions.exception.NoDataException;
import com.coding.challenge.transactions.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * After Authenticating and Authorizing the customer get the list of accounts
     */
    @RequestMapping(value = "/getCustomerAccounts", method = RequestMethod.POST,
            consumes = {APPLICATION_JSON_VALUE}, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Account>> getUserAccounts(@RequestBody Customer customer) {

        if(customer == null) {
            throw new CustomerNotFoundException("Missing Customer details!!!");
        }

        if(customer.getCustomerId() == null || customer.getCustomerId().isEmpty()) {
            throw new CustomerNotFoundException("Missing Customer ID!!!");
        }

        List<Account> accountsList = accountService.getCustomerAccounts(customer.getCustomerId());

        if(accountsList == null || accountsList.size() == 0) {
            throw new NoDataException("No accounts for the customerId "+customer.getCustomerId());
        }
        return new ResponseEntity<>(accountsList, OK);
    }

}
