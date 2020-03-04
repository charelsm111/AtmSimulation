package com.app.atmsimulation.service;

import com.app.atmsimulation.model.Account;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {

    public Account login(String accountNumber, String pin);

    public Account save(Account account);
}
