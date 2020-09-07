package com.coding.challenge.transactions.repository;

import com.coding.challenge.transactions.entity.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountTransactionsRepository extends JpaRepository<AccountTransaction, Long> {
    List<AccountTransaction> findByAccountNumber(String accountNumber);
}
