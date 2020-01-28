package com.app.atmsimulation;

public abstract class Transaction implements Comparable<Transaction> {

    String accountNumber;
    String date;
    int amount;
    String type;
    String destinationAccountNumber;

    public abstract void setType();

    @Override
    public int compareTo(Transaction transaction) {
        return this.date.compareTo(transaction.date);
    }
}
