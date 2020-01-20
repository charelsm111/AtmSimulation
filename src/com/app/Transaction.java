package com.app;

class Transaction implements Comparable<Transaction> {

    private String accountNumber;
    private String date;
    private Integer amount;
    private String type;
    private String destinationAccountNumber;

    void setDate(String date) {
        this.date = date;
    }

    String getDate() {
        return this.date;
    }

    void setAmount(Integer amount) {
        this.amount = amount;
    }

    Integer getAmount() {
        return this.amount;
    }

    void setType(String type) {
        this.type = type;
    }

    String getType() {
        return this.type;
    }

    void setDestinationAccountNumber(String destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
    }

    String getDestinationAccountNumber() {
        return this.destinationAccountNumber;
    }

    void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    String getAccountNumber() {
        return this.accountNumber;
    }

    @Override
    public int compareTo(Transaction transaction) {
        return this.getDate().compareTo(transaction.getDate());
    }

}
