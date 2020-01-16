package com.app;

class Transaction {

    Account account;
    String date;
    Integer amount;

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

    void saveToFile() {}
}
