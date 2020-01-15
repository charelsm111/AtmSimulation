package com.app;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
*   Class to storing and fetching the active member
 */
class ActiveAccount {

    private List<Account> accounts;
    private static final String FILE_NAME = "accounts.csv";
    private static final String FILE_DIR = "files";

    ActiveAccount() {
        this.accounts = new ArrayList<>();

        Account account1 = new Account();
        account1.setName("John Doe");
        account1.setPin("012108");
        account1.setBalance(100);
        account1.setAccountNumber("112233");
        accounts.add(account1);

        Account account2 = new Account();
        account2.setName("Jane Doe");
        account2.setPin("932012");
        account2.setBalance(30);
        account2.setAccountNumber("112244");
        accounts.add(account2);

        this.setAccounts(accounts);
    }

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

    void readAccountsFromFile() {
        try {
            File file = new File(ActiveAccount.FILE_DIR + "/" + ActiveAccount.FILE_NAME);
            FileReader fileReader = new FileReader(file);
            String line;

            BufferedReader reader = new BufferedReader(fileReader);
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                // TODO: Save data to Account Object
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
