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

    public Validation validateRemainingBalance(Integer amount) {
        Validation validation = new Validation();
        if (this.balance < amount) {
            validation.setIsError(true);
            validation.setMessage("Insufficient Balance " + this.balance);
        }

        return validation;
    }

}
