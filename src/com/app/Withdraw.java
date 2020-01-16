package com.app;

import java.io.*;

class Withdraw extends Transaction {

    private String date;
    private Integer amount;
    private Integer balance;

    void setDate(String date) {
        this.date = date;
    }

    private String getDate() {
        return this.date;
    }

    void setAmount(Integer amount) {
        this.amount = amount;
    }

    private Integer getAmount() {
        return this.amount;
    }

    void setBalance(Integer balance) {
        this.balance = balance;
    }

    private Integer getBalance() {
        return this.balance;
    }

    Withdraw(Account account) {
        this.account = account;
    }

    void saveToFile() {
        try {
            // Save withdraw record
            File withdraw = new File("files/withdraw.csv");
            if (withdraw.createNewFile()) {
                String record = this.account.getAccountNumber() + "," +
                        this.getDate() + "," +
                        this.getAmount() + "," +
                        this.getBalance();
                BufferedWriter writer = new BufferedWriter(new FileWriter(withdraw));
                writer.write(record);
                writer.close();
            } else {
                String record = this.account.getAccountNumber() + "," +
                        this.getDate() + "," +
                        this.getAmount() + "," +
                        this.getBalance();
                BufferedWriter writer = new BufferedWriter(new FileWriter(withdraw, true));
                writer.append("\n");
                writer.append(record);
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
