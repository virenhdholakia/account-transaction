package com.coding.challenge.transactions.controller;

import com.coding.challenge.transactions.entity.AccountTransaction;
import com.coding.challenge.transactions.exception.InvalidAccountNumberException;
import com.coding.challenge.transactions.exception.NoDataException;
import com.coding.challenge.transactions.service.AccountTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/transactions")
public class AccountTransactionController {

    @Autowired
    private AccountTransactionService accountTransactionService;

    @RequestMapping(value = "/getAccountTransactions/{accountNumber}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<AccountTransaction>> getAccountTransactions(@PathVariable("accountNumber") String accountNumber) {

        if(accountNumber == null || "0".equals(accountNumber)) {
            throw new InvalidAccountNumberException("Account Number is invalid");
        }

        List<AccountTransaction> accountTransactionsList = accountTransactionService.getUserAccountTransactions(accountNumber);

        if(accountTransactionsList == null || accountTransactionsList.size() == 0) {
            throw new NoDataException("No transactions for the account number "+accountNumber);
        }

        return new ResponseEntity<>(accountTransactionsList, OK);

    }
}
