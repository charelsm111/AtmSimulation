package com.app.atmsimulation;


import com.app.atmsimulation.model.Account;
import com.app.atmsimulation.model.Transfer;
import com.app.atmsimulation.repository.AccountRepository;
import com.app.atmsimulation.repository.TransferRepository;
import com.app.atmsimulation.service.TransferServiceImpl;
import com.app.atmsimulation.validator.AccountExistenceValidator;
import com.app.atmsimulation.validator.BalanceValidator;
import com.app.atmsimulation.validator.TransferValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
public class TransferServiceTest {

    @Mock
    TransferRepository transferRepository;

    @Mock
    AccountRepository accountRepository;

    @InjectMocks
    TransferServiceImpl transferService;

    @InjectMocks
    TransferValidator transferValidator;

    @Test
    public void testTransfer() {
        when(accountRepository.findByAccountNumber("112233")).thenReturn(new Account("112233", "Charel Samuel", 100, "123456"));

        Account account = accountRepository.findByAccountNumber("112233");
        Transfer transfer = new Transfer(10, account, "112244");

        when(transferRepository.save(transfer)).thenReturn(transfer);
        when(accountRepository.findByAccountNumber("112244")).thenReturn(new Account("112244", "Jonathan", 200, "123456"));

        Account destinationAccount = accountRepository.findByAccountNumber("112244");
        Transfer created = transferService.save(transfer);

        assertEquals(created.getDestinationAccountNumber(), transfer.getDestinationAccountNumber());
        assertEquals(Integer.valueOf(90), transfer.getAccount().getBalance());
        assertEquals(Integer.valueOf(210), destinationAccount.getBalance());
    }

    @Test
    public void testEnoughBalanceToTransfer() {
        when(accountRepository.findByAccountNumber("112233")).thenReturn(new Account("112233", "Charel Samuel", 100, "123456"));

        Account account = accountRepository.findByAccountNumber("112233");
        Transfer transfer = new Transfer(101, account, "112244");

        Errors errors = new BeanPropertyBindingResult(transfer, "transfer");
        transferValidator.validate(transfer, errors);

        List<String> errorList = errors.getFieldErrors().stream()
                .map(fieldError -> fieldError.getCode())
                .collect(Collectors.toList());

        assertThat(errorList, hasItem("withdrawAmount.insufficientBalance"));
    }

    @Test
    public void testIfDestinationAccountExist() {
        when(accountRepository.findByAccountNumber("112233")).thenReturn(new Account("112233", "Charel", 100, "123456"));
        when(accountRepository.findByAccountNumber("112244")).thenReturn(new Account("112244", "Jonathan", 200, "123456"));


        Account account = accountRepository.findByAccountNumber("112233");
        Transfer transfer = new Transfer(101, account, "112244");

        Errors errors = new BeanPropertyBindingResult(transfer, "transfer");
        transferValidator.validate(transfer, errors);
        List<String> errorList = errors.getFieldErrors().stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        assertThat(errorList, not(hasItem("destinationAccountNumber.notFound")));

        Transfer transfer1 = new Transfer(101, account, "112255");
        Errors errors2 = new BeanPropertyBindingResult(transfer, "transfer");
        transferValidator.validate(transfer1, errors2);
        errorList = errors2.getFieldErrors().stream()
                .map(fieldError -> fieldError.getCode())
                .collect(Collectors.toList());

        assertThat(errorList, hasItem("destinationAccountNumber.notFound"));
    }
}
