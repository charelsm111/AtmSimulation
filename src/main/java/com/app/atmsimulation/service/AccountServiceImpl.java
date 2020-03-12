package com.app.atmsimulation.service;

import com.app.atmsimulation.model.Account;
import com.app.atmsimulation.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account login(String accountNumber, String pin) {
        
        return accountRepository.findByAccountNumberAndPin(accountNumber, pin);
    }

    public Account save(Account account) {

        return accountRepository.save(account);
    }
}
