package com.app;

import java.util.Random;

class Account {

    private static final Integer DECREASE_TEN = 10;
    private static final Integer DECREASE_FIFTY = 50;
    private static final Integer DECREASE_HUNDRED = 100;
    private static final Integer MAX_WITHDRAWAL_AMOUNT = 1000;
    private static final Integer MAX_TRANSFER_AMOUNT = 1000;
    private static final Integer MIN_TRANSFER_AMOUNT = 1;

    private String name;
    private String pin;
    private Integer balance;
    private String accountNumber;
    private Integer withdrawal;
    private Integer transferAmount;
    private String referenceNumber;

    void setName(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    void setPin(String pin) {
        this.pin = pin;
    }

    String getPin() {
        return pin;
    }

    void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    String getAccountNumber() {
        return accountNumber;
    }

    void setBalance(Integer balance) {
        this.balance = balance;
    }

    Integer getBalance() { return balance; }

    void setTransferAmount(Integer transferAmount) {
        this.transferAmount = transferAmount;
    }

    Integer getTransferAmount() {
        return this.transferAmount;
    }

    void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    String getReferenceNumber() {
        return this.referenceNumber;
    }

    Integer getWithdrawal() { return withdrawal; }

    void setWithdrawal(Integer withdrawal) {
        this.withdrawal = withdrawal;
    }

    boolean decreaseBalanceByTen() {
        this.withdrawal = DECREASE_TEN;

        Validation validateRemainingBalance = this.validateRemainingBalance();
        if (validateRemainingBalance.getIsError()) {
            System.out.println(validateRemainingBalance.getMessage());
            return false;
        }

        this.balance = this.balance - DECREASE_TEN;
        return true;
    }

    boolean decreaseBalanceByFifty() {
        this.withdrawal = DECREASE_FIFTY;

        Validation validateRemainingBalance = this.validateRemainingBalance();
        if (validateRemainingBalance.getIsError()) {
            System.out.println(validateRemainingBalance.getMessage());
            return false;
        }

        this.balance = this.balance - DECREASE_FIFTY;
        return true;
    }

    boolean decreaseBalanceByHundred() {
        this.withdrawal = DECREASE_HUNDRED;

        Validation validateRemainingBalance = this.validateRemainingBalance();
        if (validateRemainingBalance.getIsError()) {
            System.out.println(validateRemainingBalance.getMessage());
            return false;
        }

        this.balance = this.balance - DECREASE_HUNDRED;
        return true;
    }

    void decreaseBalance() {

        this.balance = this.balance - this.withdrawal;
    }

    Validation validateRemainingBalance() {
        Validation validation = new Validation();
        if (this.balance < this.withdrawal) {
            validation.setIsError();
            validation.setMessage("Insufficient Balance $" + this.withdrawal);
        }

        return validation;
    }

    Validation validateMaximumWithdraw() {
        Validation validation = new Validation();
        if (this.withdrawal > MAX_WITHDRAWAL_AMOUNT) {
            validation.setIsError();
            validation.setMessage("Maximum amount to withdraw is $" + MAX_WITHDRAWAL_AMOUNT);
        }

        return validation;
    }


    Validation validateWithdrawAmountIsNumeric() {
        String amount = Integer.toString(this.withdrawal);

        Validation validation = new Validation();
        if (!amount.matches("[0-9]+")) {
            validation.setIsError();
            validation.setMessage("Amount should only contains numbers");
        }

        return validation;
    }


    Validation validateWithdrawAmountIsTenMultiply() {
        Validation validation = new Validation();
        if (this.withdrawal % 10 != 0) {
            validation.setIsError();
            validation.setMessage("Invalid amount");
        }

        return validation;
    }

    Validation validateMaximumTransfer() {
        Validation validation = new Validation();
        if (this.getTransferAmount() > MAX_TRANSFER_AMOUNT) {
            validation.setIsError();
            validation.setMessage("Maximum amount to withdraw is $" + MAX_TRANSFER_AMOUNT);
        }

        return validation;
    }

    Validation validateMinimumTransfer() {
        Validation validation = new Validation();
        if (this.getTransferAmount() < MIN_TRANSFER_AMOUNT) {
            validation.setIsError();
            validation.setMessage("Minimum amount to withdraw is $" + MIN_TRANSFER_AMOUNT);
        }

        return validation;
    }

    Validation validateTransferAmountIsNumeric() {
        String amount = Integer.toString(this.transferAmount);

        Validation validation = new Validation();
        if (!amount.matches("[0-9]+")) {
            validation.setIsError();
            validation.setMessage("Transfer amount should only contains numbers");
        }

        return validation;
    }

    String generateReferenceNumber() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }

    void transferFund(Account destinationAccount) {
        destinationAccount.balance = destinationAccount.balance + this.transferAmount;
        this.balance = this.balance - this.transferAmount;
    }

    Validation validateAccountNumberLength() {
        Validation validation = new Validation();

        if (this.accountNumber.length() < 6) {
            validation.setIsError();
            validation.setMessage("Account Number should have 6 digits length");
        }

        return validation;
    }


    Validation validatePinLength() {
        Validation validation = new Validation();

        if (this.pin.length() < 6) {
            validation.setIsError();
            validation.setMessage("PIN should have 6 digits length");
        }

        return validation;
    }

    Validation validatePinIsNumeric() {
        Validation validation = new Validation();

        if (!this.pin.matches("[0-9]+")) {
            validation.setIsError();
            validation.setMessage("PIN should only contains numbers");
        }

        return validation;
    }

    Validation validateAccountNumberIsNumeric() {
        Validation validation = new Validation();

        if (!this.accountNumber.matches("[0-9]+")) {
            validation.setIsError();
            validation.setMessage("Account Number should only contains numbers");
        }

        return validation;
    }

}
