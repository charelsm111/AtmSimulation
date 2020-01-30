package com.app.atmsimulation;

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

public class AccountRepository {

    static final String PATHNAME = "accounts.csv";
    private static AccountRepository instance = null;

    List<Account> accountList = new ArrayList<Account>();
    Map<String, Account> accountMap = new HashMap<>();
    Account activeAccount;

    private AccountRepository() {
        readFile();
        setAccountList();
    }

    void setAccountList() {
        for(Map.Entry<String, Account> accountEntry: accountMap.entrySet()) {
            accountList.add(accountEntry.getValue());
        }
    }

    void readFile() {
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static AccountRepository getInstance() {
        if (instance == null) {
            instance = new AccountRepository();
        }

        return instance;
    }

    public Account find(String accountNumber, String pin) {

        return accountList.stream()
                .filter(account -> accountNumber.equals(account.getAccountNumber()) && pin.equals(account.getPin()))
                .findAny()
                .orElse(null);
    }

    public Account find(String accountNumber) {

        return accountList.stream()
                .filter(account -> accountNumber.equals(account.getAccountNumber()))
                .findAny()
                .orElse(null);
    }
}
