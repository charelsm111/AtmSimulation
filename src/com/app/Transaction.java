package com.app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class Transaction {

    Account account;
    String date;
    Integer amount;
    String type;
    String record;

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

    String getRecord() {
        return this.record;
    }

    void saveToFile() {
        try {
            File transfer = new File("files/transactions.csv");
            if (transfer.createNewFile()) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(transfer));
                writer.write(this.getRecord());
                writer.close();
            } else {
                BufferedWriter writer = new BufferedWriter(new FileWriter(transfer, true));
                writer.append("\n");
                writer.append(this.getRecord());
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
