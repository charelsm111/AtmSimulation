package com.app.atmsimulation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String accountNumber;

    @Column
    private String name;

    @Column
    private int balance;

    @Column
    private String pin;

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
}
