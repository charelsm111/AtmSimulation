package com.app;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
*   Class to storing and fetching the active member
 */
class ActiveAccount {

    List<Account> accounts;
    static final String PATHNAME = "files/accounts.csv";

    void setAccounts(Map<String, Account> accountMaps) {

        List<Account> accounts = new ArrayList<>();

        for(Map.Entry<String, Account> accountEntry: accountMaps.entrySet()) {
            accounts.add(accountEntry.getValue());
        }

        this.accounts = accounts;
    }

    Account getAccount(String accountNumber, String pin) {

        return accounts.stream()
                .filter(account -> accountNumber.equals(account.getAccountNumber()) && pin.equals(account.getPin()))
                .findAny()
                .orElse(null);
    }

    Account getAccount(String accountNumber) {

        return accounts.stream()
                .filter(acc -> accountNumber.equals(acc.getAccountNumber()))
                .findAny()
                .orElse(null);
    }

    Validation validateAccountExistence(String accountNumber, String pin) {
        Account verifiedAccount = this.getAccount(accountNumber, pin);

        Validation validation = new Validation();
        if (verifiedAccount == null) {
            validation.setIsError();
            validation.setMessage("Invalid Account Number/PIN if records is not exist");
        }

        return validation;
    }

    Validation validateAccountNumberIsNumeric(String accountNumber) {
        Validation validation = new Validation();

        if (!accountNumber.matches("[0-9]+")) {
            validation.setIsError();
            validation.setMessage("Invalid account");
        }

        return validation;
    }

    Account getDestinationAccount(String accountNumber) {

        return this.getAccount(accountNumber);
    }

    Validation validateDestinationAccountNumber(String accountNumber) {
        Account verifiedAccount = getAccount(accountNumber);

        Validation validation = new Validation();
        if (verifiedAccount == null) {
            validation.setIsError();
            validation.setMessage("Invalid account");
        }

        return validation;
    }

    void setAccountsFromFile() {
        try (Stream<String> stream = Files.lines(Paths.get(PATHNAME))) {
            List<String> lines = stream.collect(Collectors.toList());

            Map<String, Account> accountMap = new HashMap<>();
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

            this.setAccounts(accountMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
