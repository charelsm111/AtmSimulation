package com.app;

import java.io.*;

class Withdraw extends Transaction {

    Withdraw(Account account) {
        this.account = account;
    }

    // TODO: Should be saved in object format
    @Override
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
