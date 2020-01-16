package com.app;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Transaction {

    private static final String PATHNAME = "files/transactions.csv";

    private String accountNumber;
    private String date;
    private Integer amount;
    private String type;
    private String destinationAccountNumber;
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

    List<Transaction> getAllTransactionsFromFile() {
        File file = new File(PATHNAME);
        List<Transaction> transactions = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(file);
            String line;

            BufferedReader reader = new BufferedReader(fileReader);
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Transaction transaction = new Transaction();
                transaction.setType(data[0]);
                transaction.setAccountNumber(data[1]);
                transaction.setDate(data[2]);
                Integer amount = Integer.parseInt(data[3]);
                transaction.setAmount(amount);
                if (data.length  > 4) {
                    transaction.setDestinationAccountNumber(data[4]);
                }
                transactions.add(transaction);
            }

            return transactions;
        } catch (IOException e) {
            System.out.printf("%s not found\n", PATHNAME);
            return transactions;
        }
    }

}
