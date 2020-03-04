package com.app.atmsimulation.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @Temporal(TemporalType.DATE)
    private Date date = new Date();

    @Column
    private int amount;

    @Column
    private String destinationAccountNumber;

    @ManyToOne
    private Account account;

    public Transaction() {}

    public Transaction(int amount, Account account, String destinationAccountNumber) {
        this.setAmount(amount);
        this.setAccount(account);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Column(name="type", insertable = false, updatable = false)
    protected String type;

    public String getType() {
        return type;
    }

    public void setDestinationAccountNumber(String destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
    }

    public String getDestinationAccountNumber() {
        return destinationAccountNumber;
    }

 }
