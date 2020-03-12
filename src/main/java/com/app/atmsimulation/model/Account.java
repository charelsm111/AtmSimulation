package com.app.atmsimulation.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String accountNumber;

    @Column
    private String name;

    @Column
    private Integer balance;

    @Column
    private String pin;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Transaction> transactionList;

    public Account() {}

    public Account(String accountNumber, String name, Integer balance, String pin) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
        this.pin = pin;
    }

    public Long getId() {
        return id;
    }

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

    public void withdraw(int amount) {
        balance = balance - amount;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public List<Transaction> getTransactionList() {
        return this.transactionList;
    }

}
