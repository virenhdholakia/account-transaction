package com.coding.challenge.transactions.service;

import com.coding.challenge.transactions.entity.AccountTransaction;
import com.coding.challenge.transactions.repository.AccountTransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountTransactionService {

    @Autowired
    private AccountTransactionsRepository accountTransactionsRepository;

    public List<AccountTransaction> getUserAccountTransactions(String accountNumber) {
        return accountTransactionsRepository.findByAccountNumber(accountNumber);
    }
}
