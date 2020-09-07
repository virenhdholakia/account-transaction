package com.coding.challenge.transactions.repository;

import com.coding.challenge.transactions.entity.Account;
import com.coding.challenge.transactions.enums.AccountTypes;
import com.coding.challenge.transactions.enums.Currency;
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
public class AccountRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository accountDetailRepository;

    @Test
    public void findByUuid_for0Accounts() {
        //given
        Account accountDetail = getAccount();

        //when
        List<Account> myAccounts = accountDetailRepository.findByCustomerId("0");

        //then
        assertThat(myAccounts.size()).isEqualTo(0);
    }

    @Test
    public void findByUuid_for1Accounts() {
        //given
        Account account = getAccount();

        //when
        List<Account> myAccounts = accountDetailRepository.findByCustomerId(account.getCustomerId());

        //then
        assertThat(myAccounts.size()).isEqualTo(1);
        assertThat(myAccounts.get(0).getCustomerId()).isEqualTo(account.getCustomerId());
    }

    @Test
    public void findByUuid_forMultipleAccounts() {
        //given
        getAccount();
        getAccount();
        getAccount();

        //when
        List<Account> myAccounts = accountDetailRepository.findByCustomerId("1");

        //then
        assertThat(myAccounts.size()).isEqualTo(3);
    }

    private Account getAccount() {
        Account account = new Account();
        Random random = new Random();
        account.setCustomerId("1");
        account.setAccountNumber(Integer.toString(random.nextInt()));
        account.setBalance(Double.toString(random.nextDouble()));
        account.setCurrency(Currency.values()[random.nextInt(Currency.values().length)]);
        account.setAccountType(AccountTypes.values()[random.nextInt(AccountTypes.values().length)]);
        account.setAccountName("Test");
        account.setBalanceDate(new Date());

        entityManager.persist(account);
        entityManager.flush();

        return account;
    }
}
