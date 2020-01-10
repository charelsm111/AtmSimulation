package com.app;

import java.util.ArrayList;
import java.util.List;

/*
*   Class to storing and fetching the active member
 */
class ActiveAccount {

    private List<Account> accounts;

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

        return checkAccount(accountNumber, pin);
    }

    private Account checkAccount(String accountNumber, String pin) {

        return accounts.stream()
                .filter(account -> accountNumber.equals(account.getAccountNumber()) && pin.equals(account.getPin()))
                .findAny()
                .orElse(null);
    }

    private Validation validateAccountExistence(String accountNumber, String pin) {
        Account verifiedAccount = checkAccount(accountNumber, pin);

        Validation validation = new Validation();
        if (verifiedAccount == null) {
            validation.setIsError();
            validation.setMessage("Invalid Account Number/PIN if records is not exist");
        }

        return validation;
    }

    private Validation validateAccountNumberLength(String accountNumber) {
        Validation validation = new Validation();

        if (accountNumber.length() < 6) {
            validation.setIsError();
            validation.setMessage("Account Number should have 6 digits length");
        }

        return validation;
    }

    private Validation validateAccountNumberIsNumeric(String accountNumber) {
        Validation validation = new Validation();

        if (!accountNumber.matches("[0-9]+")) {
            validation.setIsError();
            validation.setMessage("Account Number should only contains numbers");
        }

        return validation;
    }

    private Validation validatePinLength(String pin) {
        Validation validation = new Validation();

        if (pin.length() < 6) {
            validation.setIsError();
            validation.setMessage("PIN should have 6 digits length");
        }

        return validation;
    }

    private Validation validatePinIsNumeric(String pin) {
        Validation validation = new Validation();

        if (!pin.matches("[0-9]+")) {
            validation.setIsError();
            validation.setMessage("PIN should only contains numbers");
        }

        return validation;
    }

    Account getDestinationAccount(String accountNumber) {
        Validation validateAccountNumberIsNumeric = validateAccountNumberIsNumeric(accountNumber);
        if (validateAccountNumberIsNumeric.getIsError()) {
            System.out.println(validateAccountNumberIsNumeric.getMessage());
            return null;
        }

        Validation validateDestinationAccountNumber = this.validateDestinationAccountNumber(accountNumber);
        if (validateDestinationAccountNumber.getIsError()) {
            System.out.println(validateDestinationAccountNumber.getMessage());
            return null;
        }

        return this.getAccountByAccountNumber(accountNumber);
    }

    private Validation validateDestinationAccountNumber(String accountNumber) {
        Account verifiedAccount = getAccountByAccountNumber(accountNumber);

        Validation validation = new Validation();
        if (verifiedAccount == null) {
            validation.setIsError();
            validation.setMessage("Invalid account");
        }

        return validation;
    }

    private Account getAccountByAccountNumber(String accountNumber) {

        return accounts.stream()
                .filter(acc -> accountNumber.equals(acc.getAccountNumber()))
                .findAny()
                .orElse(null);
    }
}
