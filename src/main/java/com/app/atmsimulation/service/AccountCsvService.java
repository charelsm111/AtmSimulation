package com.app.atmsimulation.service;

import com.app.atmsimulation.model.Account;
import org.springframework.stereotype.Service;

@Service
public interface AccountCsvService {

    public Account save(Account account);

    public Account findByAccountNumber(String accountNumber);
}
