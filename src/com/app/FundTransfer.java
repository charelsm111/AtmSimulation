package com.app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FundTransfer extends Transaction {

    String destinationAccountNumber;

    FundTransfer(Account account) {
        this.account = account;
    }

    void setDestinationAccountNumber(String destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
    }

    String getDestinationAccountNumber() {
        return this.destinationAccountNumber;
    }

    // TODO: Should merged to superclass and have only 1 csv file
    @Override
    void saveToFile() {
        try {
            String record = this.account.getAccountNumber() + "," +
                    this.getDate() + "," +
                    this.getAmount() + "," +
                    this.getDestinationAccountNumber();

            // Save withdraw record
            File transfer = new File("files/transfer.csv");
            if (transfer.createNewFile()) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(transfer));
                writer.write(record);
                writer.close();
            } else {
                BufferedWriter writer = new BufferedWriter(new FileWriter(transfer, true));
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
