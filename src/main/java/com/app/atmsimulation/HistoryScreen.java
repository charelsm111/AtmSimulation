package com.app.atmsimulation;

import java.util.List;
import java.util.Scanner;

public class HistoryScreen extends TransactionScreen {

    public void show(List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println("No transactions");
        } else {
            Integer no = 1;
            for (Transaction transaction: transactions) {
                if (transaction.type.equals("withdraw")) {
                    System.out.printf("%d.%s %s-> amount: $%s\n", no, transaction.date, transaction.type,
                            transaction.amount);
                } else {
                    System.out.printf("%d.%s %s-> amount: $%s to: %s\n", no, transaction.date, transaction.type,
                            transaction.amount, transaction.destinationAccountNumber);
                }
                no++;
            }
        }

        Scanner in = new Scanner(System.in);
        System.out.print("Please enter an amount of last transactions or \n" +
                "press enter to go back to Transaction: ");
        String answer = in.nextLine();

        if (answer.equals("")) {
            super.show();
        }

        int limit = Integer.parseInt(answer);
        List<Transaction> otherTransactions = transactionRepository.find(activeAccount.getAccountNumber(), limit);
        show(otherTransactions);
    }
}
