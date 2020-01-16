package com.app;

import java.io.*;

class Withdraw extends Transaction {

    private String date;
    private Integer amount;

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

    Withdraw(Account account) {
        this.account = account;
    }

    // TODO: Should be saved in object format
    void saveToFile() {
        try {
            String record = this.account.getAccountNumber() + "," +
                    this.getDate() + "," +
                    this.getAmount();

            // Save withdraw record
            File withdraw = new File("files/withdraw.csv");
            if (withdraw.createNewFile()) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(withdraw));
                writer.write(record);
                writer.close();
            } else {
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
