package com.charel;

public class Account {

    private String name;
    private String pin;
    private Integer balance;
    private String accountNumber;
    private Integer withdrawal;

    final Integer DECREASE_TEN = 10;
    final Integer DECREASE_FIFTY = 50;
    final Integer DECREASE_HUNDRED = 100;
    final Integer MAX_WITHDRAWAL_AMOUNT = 1000;

    public Account(String name, String pin, Integer balance, String accountNumber) {
        this.name = name;
        this.pin = pin;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public String getPin() {
        return pin;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Integer getBalance() { return balance; }

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
            validation.setMessage("Insufficient Balance " + amount);
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


}
