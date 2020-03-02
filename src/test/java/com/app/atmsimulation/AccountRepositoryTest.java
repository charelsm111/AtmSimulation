package com.app.atmsimulation;

import com.app.atmsimulation.model.Account;
import com.app.atmsimulation.repository.AccountRepository;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testFindByAccountNumber() {
        Account account = new Account("112233", "Charel Samuel", 100, "123456");

        entityManager.persist(account);

        Account newAccount = accountRepository.findByAccountNumber("112233");
        assertEquals("112233", newAccount.getAccountNumber());
        assertEquals("Charel Samuel", newAccount.getName());
        assertEquals(100, newAccount.getBalance());
        assertEquals("123456", newAccount.getPin());
    }

    @Test
    public void testFindByAccountNumberAndPin() {
        Account account = new Account("112233", "Charel Samuel", 100, "123456");

        entityManager.persist(account);

        Account newAccount = accountRepository.findByAccountNumberAndPin("112233", "123456");
        assertEquals("112233", newAccount.getAccountNumber());
        assertEquals("Charel Samuel", newAccount.getName());
        assertEquals(100, newAccount.getBalance());
        assertEquals("123456", newAccount.getPin());
    }

    @Test
    public void testFindAll() {
        Account account1 = new Account("112233", "Charel Samuel", 100, "123456");
        Account account2 = new Account("112244", "Jonathan", 200, "111111");
        entityManager.persist(account1);
        entityManager.persist(account2);

        List<Account> accounts = accountRepository.findAll();
        assertEquals(2, accounts.size());
        assertTrue(accounts.stream().anyMatch(account -> "112233".equals(account.getAccountNumber())));
    }
}
