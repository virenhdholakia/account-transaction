package com.coding.challenge.transactions.repository;

import com.coding.challenge.transactions.entity.AccountTransaction;
import com.coding.challenge.transactions.enums.Currency;
import com.coding.challenge.transactions.enums.TransactionType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountTransactionsRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountTransactionsRepository accountTransactionsRepository;

    @Test
    public void findByAccountNumberTest_for0Transaction() {
        AccountTransaction accountTransaction = getAccountTransactionWithAccountNumber(Integer.toString(new Random().nextInt()));
        getAccountTransaction(accountTransaction);

        entityManager.persistAndFlush(accountTransaction);

        List<AccountTransaction> myAccountTransactionsList =
                accountTransactionsRepository.findByAccountNumber("0");

        assertThat(myAccountTransactionsList.size()).isEqualTo(0);

    }

    @Test
    public void findByAccountNumberTest_for1Transaction() {
        AccountTransaction accountTransaction = getAccountTransactionWithAccountNumber(Integer.toString(new Random().nextInt()));
        getAccountTransaction(accountTransaction);

        entityManager.persistAndFlush(accountTransaction);

        List<AccountTransaction> myAccountTransactionsList =
                accountTransactionsRepository.findByAccountNumber(accountTransaction.getAccountNumber());

        assertThat(myAccountTransactionsList.size()).isEqualTo(1);
        assertThat(myAccountTransactionsList.get(0).getAccountNumber()).isEqualTo(accountTransaction.getAccountNumber());

    }

    @Test
    public void findByAccountNumberTest_forMultipleTransactions() {
        String accountNumber = "12345";
        AccountTransaction accountTransaction1 = getAccountTransactionWithAccountNumber(accountNumber);
        AccountTransaction accountTransaction2 = getAccountTransactionWithAccountNumber(accountNumber);
        AccountTransaction accountTransaction3 = getAccountTransactionWithAccountNumber(accountNumber);
        getAccountTransaction(accountTransaction1);
        getAccountTransaction(accountTransaction2);
        getAccountTransaction(accountTransaction3);
        entityManager.persist(accountTransaction1);
        entityManager.persist(accountTransaction2);
        entityManager.persist(accountTransaction3);
        entityManager.flush();

        List<AccountTransaction> myAccountTransactionsList =
                accountTransactionsRepository.findByAccountNumber(accountNumber);

        assertThat(myAccountTransactionsList.size()).isEqualTo(3);

    }

    private AccountTransaction getAccountTransaction(AccountTransaction accountTransaction) {
        Random random = new Random();

        accountTransaction.setTransactionNarrative("Test Transaction");
        accountTransaction.setTransactionType(TransactionType.values()[random.nextInt(TransactionType.values().length)]);
        accountTransaction.setTransactionCurrency(Currency.values()[random.nextInt(Currency.values().length)]);
        accountTransaction.setTransactionDate(new Date());
        accountTransaction.setAmount(Double.toString(random.nextDouble()));

        return accountTransaction;
    }

    private AccountTransaction getAccountTransactionWithAccountNumber(String accountNumber) {

        AccountTransaction accountTransaction = new AccountTransaction();

        accountTransaction.setAccountNumber(accountNumber);

        return accountTransaction;

    }
}
