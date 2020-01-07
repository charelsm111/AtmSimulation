package com.charel;

public class Account {

    private String name;
    private String pin;
    private Integer balance;
    private String accountNumber;

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
}
