package com.app;

public class Account {

    private String name;
    private String pin;
    private Integer balance;
    private String accountNumber;

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

    public void withdraw(int amount) {
        balance = balance - amount;
    }

    public void transfer(String destinationAccountNumber, int transferAmount) {
        AccountRepository accountRepository = AccountRepository.getInstance();
        Account destinationAccount = accountRepository.find(destinationAccountNumber);

        destinationAccount.balance = destinationAccount.balance + transferAmount;
        this.balance = this.balance - transferAmount;
    }

    public boolean validateRemainingBalance(int amount) {
        int remains = this.balance - amount;

        if (remains < 0) {
            return false;
        }

        return true;
    }
}
