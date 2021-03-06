package com.app.atmsimulation.service;

import com.app.atmsimulation.model.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountCsvService {

    public Account save(Account account);

    public Account findByAccountNumber(String accountNumber);

    public void removeDuplicateAccountFromCsv(List<String> lines);

    public List<Account> getAccountList();
}
