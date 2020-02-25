package com.app.atmsimulation.service;

import com.app.atmsimulation.model.Account;
import com.app.atmsimulation.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account login(String accountNumber, String pin) {
        
        return accountRepository.findByAccountNumberAndPin(accountNumber, pin);
    }

    public void withdraw() {};

    public void transfer(String destinationAccountNumber) {};
}
