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
        Account account = transfer.getAccount();
        Integer balance = account.getBalance() - transfer.getAmount();

        account.setBalance(balance);
        accountRepository.save(account);

        return transferRepository.save(transfer);
    }
}
