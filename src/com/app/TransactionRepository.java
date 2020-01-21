package com.app;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class TransactionRepository {

    private List<Transaction> transactionList;

    TransactionRepository() {
        this.transactionList = new ArrayList<>();
    }

    void addTransaction(Transaction transaction) {
        System.out.println(transaction.getAccountNumber());
        this.transactionList.add(transaction);
    }

    private List<Transaction> getTransactionList() {
        return this.transactionList;
    }

    List<Transaction> getLastTenTransactions(Account account) {
        try {
            System.out.println(account.getAccountNumber());
            return this.getTransactionList().stream()
                    .filter(transaction1 -> transaction1.getAccountNumber().equals(account.getAccountNumber()))
                    .sorted(Comparator.reverseOrder())
                    .limit(10)
                    .collect(Collectors.toList());
        } catch (NullPointerException e) {
            System.out.println("error");
            return new ArrayList<>();
        }
    }

    List<Transaction> getTransactions(Account account, Integer amount) {
        try {
            return this.getTransactionList().stream()
                    .filter(transaction1 -> transaction1.getAccountNumber().equals(account.getAccountNumber()))
                    .sorted(Comparator.reverseOrder())
                    .limit(amount)
                    .collect(Collectors.toList());
        } catch (NullPointerException e) {
            return new ArrayList<>();
        }
    }
}
