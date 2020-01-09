package com.charel;

import java.util.Random;

public class Account {

    private String name;
    private String pin;
    private Integer balance;
    private String accountNumber;
    private Integer withdrawal;
    private Integer transferAmount;
    private String referenceNumber;

    final Integer DECREASE_TEN = 10;
    final Integer DECREASE_FIFTY = 50;
    final Integer DECREASE_HUNDRED = 100;
    final Integer MAX_WITHDRAWAL_AMOUNT = 1000;
    final Integer MAX_TRANSFER_AMOUNT = 1000;
    final Integer MIN_TRANSFER_AMOUNT = 1;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getPin() {
        return pin;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getBalance() { return balance; }

    public void setTransferAmount(Integer transferAmount) {
        this.transferAmount = transferAmount;
    }

    public Integer getTransferAmount() {
        return this.transferAmount;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getReferenceNumber() {
        return this.referenceNumber;
    }

    public Integer getWithdrawal() { return withdrawal; }

    public boolean decreaseBalanceByTen() {
        Validation validateRemainingBalance = this.validateRemainingBalance(DECREASE_TEN);
        if (validateRemainingBalance.getIsError()) {
            System.out.println(validateRemainingBalance.getMessage());
            return false;
        }

        this.withdrawal = DECREASE_TEN;
        this.balance = this.balance - DECREASE_TEN;
        return true;
    }

    public boolean decreaseBalanceByFifty() {
        Validation validateRemainingBalance = this.validateRemainingBalance(DECREASE_FIFTY);
        if (validateRemainingBalance.getIsError()) {
            System.out.println(validateRemainingBalance.getMessage());
            return false;
        }

        this.withdrawal = DECREASE_FIFTY;
        this.balance = this.balance - DECREASE_FIFTY;
        return true;
    }

    public boolean decreaseBalanceByHundred() {
        Validation validateRemainingBalance = this.validateRemainingBalance(DECREASE_HUNDRED);
        if (validateRemainingBalance.getIsError()) {
            System.out.println(validateRemainingBalance.getMessage());
            return false;
        }

        this.withdrawal = DECREASE_HUNDRED;
        this.balance = this.balance - DECREASE_HUNDRED;
        return true;
    }

    public boolean decreaseBalance(String amount) {
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

    public Validation validateRemainingBalance(Integer amount) {
        Validation validation = new Validation();
        if (this.balance < amount) {
            validation.setIsError(true);
            validation.setMessage("Insufficient Balance $" + amount);
        }

        return validation;
    }

    public Validation validateMaximumWithdraw() {
        Validation validation = new Validation();
        if (this.balance > MAX_WITHDRAWAL_AMOUNT) {
            validation.setIsError(true);
            validation.setMessage("Maximum amount to withdraw is $" + MAX_WITHDRAWAL_AMOUNT);
        }

        return validation;
    }

    public Validation validateWithdrawAmountIsNumeric(String amount) {
        Validation validation = new Validation();
        if (!amount.matches("[0-9]+")) {
            validation.setIsError(true);
            validation.setMessage("Invalid amount");
        }

        return validation;
    }

    public Validation validateWithdrawAmountIsTenMultiply(Integer amount) {
        Validation validation = new Validation();
        if (amount % 10 != 0) {
            validation.setIsError(true);
            validation.setMessage("Invalid amount");
        }

        return validation;
    }

    public Validation validateMaximumTransfer() {
        Validation validation = new Validation();
        if (this.getTransferAmount() > MAX_TRANSFER_AMOUNT) {
            validation.setIsError(true);
            validation.setMessage("Maximum amount to withdraw is $" + MAX_TRANSFER_AMOUNT);
        }

        return validation;
    }

    public Validation validateMinimumTransfer() {
        Validation validation = new Validation();
        if (this.getTransferAmount() < MIN_TRANSFER_AMOUNT) {
            validation.setIsError(true);
            validation.setMessage("Minimum amount to withdraw is $" + MIN_TRANSFER_AMOUNT);
        }

        return validation;
    }

    public Validation validateTransferAmountIsNumeric(String amount) {
        Validation validation = new Validation();
        if (!amount.matches("[0-9]+")) {
            validation.setIsError(true);
            validation.setMessage("Invalid amount");
        }

        return validation;
    }

    public String generateReferenceNumber() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }

    public void transferFund(Account destinationAccount) {
        destinationAccount.balance = destinationAccount.balance + this.transferAmount;
        this.balance = this.balance - this.transferAmount;
    }


}
