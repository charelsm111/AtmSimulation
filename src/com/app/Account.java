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

    boolean decreaseBalanceByTen() {
        Validation validateRemainingBalance = this.validateRemainingBalance(DECREASE_TEN);
        if (validateRemainingBalance.getIsError()) {
            System.out.println(validateRemainingBalance.getMessage());
            return false;
        }

        this.withdrawal = DECREASE_TEN;
        this.balance = this.balance - DECREASE_TEN;
        return true;
    }

    boolean decreaseBalanceByFifty() {
        Validation validateRemainingBalance = this.validateRemainingBalance(DECREASE_FIFTY);
        if (validateRemainingBalance.getIsError()) {
            System.out.println(validateRemainingBalance.getMessage());
            return false;
        }

        this.withdrawal = DECREASE_FIFTY;
        this.balance = this.balance - DECREASE_FIFTY;
        return true;
    }

    boolean decreaseBalanceByHundred() {
        Validation validateRemainingBalance = this.validateRemainingBalance(DECREASE_HUNDRED);
        if (validateRemainingBalance.getIsError()) {
            System.out.println(validateRemainingBalance.getMessage());
            return false;
        }

        this.withdrawal = DECREASE_HUNDRED;
        this.balance = this.balance - DECREASE_HUNDRED;
        return true;
    }

    boolean decreaseBalance(String amount) {
        Validation validateWithdrawAmountIsNumeric = this.validateWithdrawAmountIsNumeric(amount);
        if (validateWithdrawAmountIsNumeric.getIsError()) {
            System.out.println(validateWithdrawAmountIsNumeric.getMessage());
            return false;
        }

        Integer inAmount = Integer.parseInt(amount);
        Validation validateMaximumWithdraw = this.validateMaximumWithdraw();
        if (validateMaximumWithdraw.getIsError()) {
            System.out.println(validateMaximumWithdraw.getMessage());
            return false;
        }

        Validation validateRemainingBalance = this.validateRemainingBalance(inAmount);
        if (validateRemainingBalance.getIsError()) {
            System.out.println(validateRemainingBalance.getMessage());
            return false;
        }

        Validation validateWithdrawAmountIsTenMultiply = this.validateWithdrawAmountIsTenMultiply(inAmount);
        if (validateWithdrawAmountIsTenMultiply.getIsError()) {
            System.out.println(validateWithdrawAmountIsTenMultiply.getMessage());
            return false;
        }

        this.withdrawal = inAmount;
        this.balance = this.balance - inAmount;
        return true;
    }

    Validation validateRemainingBalance(Integer amount) {
        Validation validation = new Validation();
        if (this.balance < amount) {
            validation.setIsError();
            validation.setMessage("Insufficient Balance $" + amount);
        }

        return validation;
    }

    private Validation validateMaximumWithdraw() {
        Validation validation = new Validation();
        if (this.balance > MAX_WITHDRAWAL_AMOUNT) {
            validation.setIsError();
            validation.setMessage("Maximum amount to withdraw is $" + MAX_WITHDRAWAL_AMOUNT);
        }

        return validation;
    }


    private Validation validateWithdrawAmountIsNumeric(String amount) {
        Validation validation = new Validation();
        if (!amount.matches("[0-9]+")) {
            validation.setIsError();
            validation.setMessage("Invalid amount");
        }

        return validation;
    }


    private Validation validateWithdrawAmountIsTenMultiply(Integer amount) {
        Validation validation = new Validation();
        if (amount % 10 != 0) {
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

    Validation validateTransferAmountIsNumeric(String amount) {
        Validation validation = new Validation();
        if (!amount.matches("[0-9]+")) {
            validation.setIsError();
            validation.setMessage("Invalid amount");
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


}
