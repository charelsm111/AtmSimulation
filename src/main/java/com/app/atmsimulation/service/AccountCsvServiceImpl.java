package com.app.atmsimulation.service;

import com.app.atmsimulation.model.Account;
import com.app.atmsimulation.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class AccountCsvServiceImpl implements AccountCsvService {
    static final String PATHNAME = "accounts.csv";

    List<Account> accountList = new ArrayList<Account>();
    Map<String, Account> accountMap = new HashMap<>();

    @Autowired
    private AccountRepository accountRepository;

    void setAccountList() {
        for(Map.Entry<String, Account> accountEntry: accountMap.entrySet()) {
            accountList.add(accountEntry.getValue());
        }
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void readFile() {
        Path path = null;
        try {
            path = Paths.get(getClass().getClassLoader().getResource(PATHNAME).toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try (Stream<String> stream = Files.lines(path)) {
            List<String> lines = stream.collect(Collectors.toList());

            for (String line: lines) {
                String[] data = line.split(",");

                Account account = new Account();
                account.setName(data[0]);
                account.setPin(data[1]);
                Integer balance = Integer.parseInt(data[2]);
                account.setBalance(balance);
                account.setAccountNumber(data[3]);
                accountMap.put(account.getAccountNumber(), account);
            }

            setAccountList();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Account save(Account account) {

        return accountRepository.save(account);
    }

    @Override
    public Account findByAccountNumber(String accountNumber) {

        return accountRepository.findByAccountNumber(accountNumber);
    }
}
