package com.charel;

import java.util.ArrayList;
import java.util.List;

public class ActiveAccount {

    private List<Account> accounts;

    public ActiveAccount() {
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

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Account getAccount(String accountNumber, String pin) {
        Validation validateAccountNumberLength = validateAccountNumberLength(accountNumber);
        if (validateAccountNumberLength.getIsError()) {
            System.out.println(validateAccountNumberLength.getMessage());
            return null;
        }

        Validation validateAccountNumberIsNumeric = validateAccountNumberIsNumeric(accountNumber);
        if (validateAccountNumberIsNumeric.getIsError()) {
            System.out.println(validateAccountNumberIsNumeric.getMessage());
            return null;
        }

        Validation validatePinLength = validatePinLength(pin);
        if (validatePinLength.getIsError()) {
            System.out.println(validatePinLength.getMessage());
            return null;
        }

        Validation validatePinIsNumeric = validatePinIsNumeric(pin);
        if (validatePinIsNumeric.getIsError()) {
            System.out.println(validatePinIsNumeric.getMessage());
            return null;
        }

        Validation validateAccountExistence = validateAccountExistence(accountNumber, pin);
        if (validateAccountExistence.getIsError()) {
            System.out.println(validateAccountExistence.getMessage());
            return null;
        }

        Account account = checkAccount(accountNumber, pin);

        return account;
    }

    public Account checkAccount(String accountNumber, String pin) {
        Account verifiedAccount = accounts.stream()
                .filter(account -> accountNumber.equals(account.getAccountNumber()) && pin.equals(account.getPin()))
                .findAny()
                .orElse(null);

        return verifiedAccount;
    }

    public Validation validateAccountExistence(String accountNumber, String pin) {
        Account verifiedAccount = checkAccount(accountNumber, pin);

        Validation validation = new Validation();
        if (verifiedAccount == null) {
            validation.setIsError(true);
            validation.setMessage("Invalid Account Number/PIN if records is not exist");
        }

        return validation;
    }

    public Validation validateAccountNumberLength(String accountNumber) {
        Validation validation = new Validation();

        if (accountNumber.length() < 6) {
            validation.setIsError(true);
            validation.setMessage("Account Number should have 6 digits length");
        }

        return validation;
    }

    public Validation validateAccountNumberIsNumeric(String accountNumber) {
        Validation validation = new Validation();

        if (!accountNumber.matches("[0-9]+")) {
            validation.setIsError(true);
            validation.setMessage("Account Number should only contains numbers");
        }

        return validation;
    }

    public Validation validatePinLength(String pin) {
        Validation validation = new Validation();

        if (pin.length() < 6) {
            validation.setIsError(true);
            validation.setMessage("PIN should have 6 digits length");
        }

        return validation;
    }

    public Validation validatePinIsNumeric(String pin) {
        Validation validation = new Validation();

        if (!pin.matches("[0-9]+")) {
            validation.setIsError(true);
            validation.setMessage("PIN should only contains numbers");
        }

        return validation;
    }

    public Account getDestinationAccount(String accountNumber) {
        Account destinationAccount = accounts.stream()
                .filter(account -> accountNumber.equals(account.getAccountNumber()))
                .findAny()
                .orElse(null);

        return destinationAccount;
    }
}
