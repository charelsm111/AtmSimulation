package com.app.atmsimulation;


import com.app.atmsimulation.model.Account;
import com.app.atmsimulation.model.Transfer;
import com.app.atmsimulation.repository.AccountRepository;
import com.app.atmsimulation.repository.TransferRepository;
import com.app.atmsimulation.service.TransferServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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

    @Test
    public void testTransfer() {
        when(accountRepository.findByAccountNumber("112233")).thenReturn(new Account("112233", "Charel Samuel", 100, "123456"));

        Account account = accountRepository.findByAccountNumber("112233");
        Transfer transfer = new Transfer(10, account, "112244");

        when(transferRepository.save(transfer)).thenReturn(transfer);
        when(accountRepository.save(account)).thenReturn(new Account("112233", "Charel Samuel", 90, "123456"));
        when(accountRepository.findByAccountNumber("112244")).thenReturn(new Account("112244", "Jonathan", 200, "123456"));

        Account destinationAccount = accountRepository.findByAccountNumber("112244");

        Transfer created = transferService.save(transfer);

        assertEquals(created.getDestinationAccountNumber(), transfer.getDestinationAccountNumber());
        assertEquals(Integer.valueOf(90), transfer.getAccount().getBalance());
        assertEquals(Integer.valueOf(210), destinationAccount.getBalance());
    }
}
