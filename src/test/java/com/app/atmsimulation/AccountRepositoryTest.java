package com.app.atmsimulation;

import com.app.atmsimulation.model.Account;
import com.app.atmsimulation.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(properties = {"spring.datasource.data=test-data.sql"})
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testFindByAccountNumber() {
        Account account = new Account("112233", "Charel Samuel", 100, "123456");

        accountRepository.save(account);

        Account newAccount = accountRepository.findByAccountNumber("112233");
        assertEquals("112233", newAccount.getAccountNumber());
        assertEquals("Charel Samuel", newAccount.getName());
        assertEquals(Integer.valueOf(100), newAccount.getBalance());
        assertEquals("123456", newAccount.getPin());
    }

    @Test
    public void testFindByAccountNumberAndPin() {
        Account account = new Account("112233", "Charel Samuel", 100, "123456");

        accountRepository.save(account);

        Account newAccount = accountRepository.findByAccountNumberAndPin("112233", "123456");
        assertEquals("112233", newAccount.getAccountNumber());
        assertEquals("Charel Samuel", newAccount.getName());
        assertEquals(Integer.valueOf(100), newAccount.getBalance());
        assertEquals("123456", newAccount.getPin());
    }

    @Test
    public void testFindAll() {
        Account account1 = new Account("112233", "Charel Samuel", 100, "123456");
        Account account2 = new Account("112244", "Jonathan", 200, "111111");
        accountRepository.save(account1);
        accountRepository.save(account2);

        List<Account> accounts = accountRepository.findAll();
        assertEquals(3, accounts.size()); // 1 from test-data.sql
        assertTrue(accounts.stream().anyMatch(account -> "112233".equals(account.getAccountNumber())));
    }
}
