package com.app.atmsimulation.service;

import com.app.atmsimulation.model.Account;
import com.app.atmsimulation.model.Transfer;
import com.app.atmsimulation.repository.AccountRepository;
import com.app.atmsimulation.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Transfer save(Transfer transfer) {
        // Decrease the balance of Sender Account
        Account account = transfer.getAccount();
        account.setBalance(account.getBalance() - transfer.getAmount());
        accountRepository.save(account);

        // Increase the balance of Destination Account
        Account destinationAccount = accountRepository.findByAccountNumber(transfer.getDestinationAccountNumber());
        destinationAccount.setBalance(destinationAccount.getBalance() + transfer.getAmount());
        accountRepository.save(destinationAccount);

        return transferRepository.save(transfer);
    }
}
