package com.app.atmsimulation;

import com.app.atmsimulation.model.Account;
import com.app.atmsimulation.model.Transaction;
import com.app.atmsimulation.model.Transfer;
import com.app.atmsimulation.model.Withdraw;
import com.app.atmsimulation.repository.AccountRepository;
import com.app.atmsimulation.repository.TransactionRepository;
import com.app.atmsimulation.repository.TransferRepository;
import com.app.atmsimulation.repository.WithdrawRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @Autowired
    private WithdrawRepository withdrawRepository;

    @Autowired
    private TransferRepository transferRepository;

    @Test
    public void testGetTransactionInTenLastTransaction() {
        Account account = new Account("11111", "Andi", 1000, "111111");

        Account savedAccount = accountRepository.save(account);
        Transfer transfer1 = new Transfer(45, savedAccount, "333334");
        Withdraw withdraw2 = new Withdraw(10, savedAccount);
        Withdraw withdraw3 = new Withdraw(15, savedAccount);
        Transfer transfer4 = new Transfer(10, savedAccount, "222222");
        Transfer transfer5 = new Transfer(15, savedAccount, "333333");
        Withdraw withdraw6 = new Withdraw(20, savedAccount);
        Withdraw withdraw7 = new Withdraw(25, savedAccount);
        Transfer transfer8 = new Transfer(30, savedAccount, "222222");
        Transfer transfer9 = new Transfer(35, savedAccount, "333333");
        Transfer transfer10 = new Transfer(40, savedAccount, "222222");
        Transfer transfer11 = new Transfer(45, savedAccount, "333333");
        Transfer transfer12 = new Transfer(45, savedAccount, "333333");
        Transfer savedTransfer1 = transferRepository.save(transfer1);
        Withdraw savedWithdraw2 = withdrawRepository.save(withdraw2);
        Withdraw savedWithdraw3 = withdrawRepository.save(withdraw3);
        Transfer savedTransfer4 = transferRepository.save(transfer4);
        Transfer savedTransfer5 = transferRepository.save(transfer5);
        Withdraw savedWithdraw6 = withdrawRepository.save(withdraw6);
        Withdraw savedWithdraw7 = withdrawRepository.save(withdraw7);
        Transfer savedTransfer8 = transferRepository.save(transfer8);
        Transfer savedTransfer9 = transferRepository.save(transfer9);
        Transfer savedTransfer10 = transferRepository.save(transfer10);
        Transfer savedTransfer11 = transferRepository.save(transfer11);
        Transfer savedTransfer12 = transferRepository.save(transfer12);

        List<Transaction> lastTransactions = transactionRepository.findLastTransaction(10);

        assertThat(lastTransactions, hasSize(10));
        assertThat(lastTransactions, hasItem(savedTransfer12));
        assertThat(lastTransactions, not(hasItem(withdraw2)));
    }
}
