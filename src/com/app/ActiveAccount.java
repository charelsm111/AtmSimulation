package com.app;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
*   Class to storing and fetching the active member
 */
class ActiveAccount {

    private List<Account> accounts;
    private static final String PATHNAME = "files/accounts.csv";
    private boolean dataIsLoaded;

    private void setAccounts(List<Account> accounts) {
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

    void setDataIsLoaded(boolean dataIsLoaded) {
        this.dataIsLoaded = dataIsLoaded;
    }

    boolean getDataIsLoaded() {
        return this.dataIsLoaded;
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

    void getAccountsFromFile(String pathname) {
        File file = new File(pathname);
        if (pathname.equals("")) {
            file = new File(ActiveAccount.PATHNAME);
        }

        try {
            FileReader fileReader = new FileReader(file);
            String line;
            this.accounts = new ArrayList<>();

            BufferedReader reader = new BufferedReader(fileReader);
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                Account account = new Account();
                account.setName(data[0]);
                account.setPin(data[1]);
                Integer balance = Integer.parseInt(data[2]);
                account.setBalance(balance);
                account.setAccountNumber(data[3]);
                accounts.add(account);
            }

            this.setAccounts(accounts);
            if (this.getAccountHasDuplicateValue().size() >= 1) {
                for(Account account: getAccountHasDuplicateValue()) {
                    System.out.printf("You have duplicated account number for: %s\n", account.getAccountNumber());
                }
            } else {
                this.setDataIsLoaded(true);
            }
        } catch (IOException e) {
            System.out.printf("%s not found\n", pathname);
        }
    }

    List<Account> getAccountHasDuplicateValue() {

        return this.accounts.stream().collect(Collectors.groupingBy(Function.identity(),
                Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1L)
                .map(e -> e.getKey())
                .collect(Collectors.toList());
    }
}
