package com.app.atmsimulation;

import com.app.atmsimulation.model.Account;
import com.app.atmsimulation.model.Withdraw;
import com.app.atmsimulation.repository.WithdrawRepository;
import com.app.atmsimulation.repository.AccountRepository;
import com.app.atmsimulation.validator.BalanceValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.validation.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = {"spring.datasource.data=test-data.sql"})
public class WithdrawRepositoryTest {

    @Autowired
    private WithdrawRepository withdrawRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testSave() {
        AccountRepository accountRepository = mock(AccountRepository.class);
        when(accountRepository.findByAccountNumber("112233")).thenReturn(new Account("112233", "Charel", 100, "123456"));

        Account newAccount = accountRepository.findByAccountNumber("112233");
        assertEquals("112233", newAccount.getAccountNumber());

        Withdraw newWithdraw = new Withdraw(50, newAccount);
        withdrawRepository.save(newWithdraw);
        newAccount.setBalance(newAccount.getBalance() - newWithdraw.getAmount());
        accountRepository.save(newAccount);

        Withdraw withdraw = withdrawRepository.findById(1l).orElse(null);
        assertEquals(50, withdraw.getAmount());
        assertEquals("112233", withdraw.getAccount().getAccountNumber());

        Account account = accountRepository.findByAccountNumber("112233");
        assertEquals(Integer.valueOf(50), account.getBalance());
    }

    @Test
    public void testSaveWithInsufficientBalance() {
        AccountRepository accountRepository = mock(AccountRepository.class);
        when(accountRepository.findByAccountNumber("112233")).thenReturn(new Account("112233", "Charel", 100, "123456"));

        Account newAccount = accountRepository.findByAccountNumber("112233");
        assertEquals("112233", newAccount.getAccountNumber());

        Withdraw newWithdraw = new Withdraw(150, newAccount);
        Errors errors = new BeanPropertyBindingResult(newWithdraw, "Insufficient Balance");
        BalanceValidator balanceValidator = new BalanceValidator();
        balanceValidator.validate(newWithdraw, errors);

        assertTrue(errors.hasErrors());
    }
}
