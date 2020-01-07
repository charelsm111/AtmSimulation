package com.charel;

import java.util.List;

public class ActiveAccount {

    private List<Account> accounts;

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public boolean verifyAccount(String accountNumber, String pin) {
        Validation validateAccountNumberLength = validateAccountNumberLength(accountNumber);
        if (validateAccountNumberLength.getIsError()) {
            System.out.println(validateAccountNumberLength.getMessage());
            return false;
        }

        Validation validateAccountNumberIsNumeric = validateAccountNumberIsNumeric(accountNumber);
        if (validateAccountNumberIsNumeric.getIsError()) {
            System.out.println(validateAccountNumberIsNumeric.getMessage());
            return false;
        }

        Validation validateAccountExistence = validateAccountExistence(accountNumber, pin);
        if (validateAccountExistence.getIsError()) {
            System.out.println(validateAccountExistence.getMessage());
            return false;
        }

        return true;
    }

    public Validation validateAccountExistence(String accountNumber, String pin) {
        Account verifiedAccount = accounts.stream()
                .filter(account -> accountNumber.equals(account.getAccountNumber()) && pin.equals(account.getPin()))
                .findAny()
                .orElse(null);

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
}
