package com.app.atmsimulation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionRepository {

    private static TransactionRepository instance = null;
    List<Transaction> transactionList;

    private TransactionRepository() {
        transactionList = new ArrayList<>();
    }

    public static TransactionRepository getInstance() {
        if (instance == null) {
            instance = new TransactionRepository();
        }

        return instance;
    }

    public void add(Transaction transaction) {
        transactionList.add(transaction);
    }

    public List<Transaction> find(String accountNumber, int limit) {

        return transactionList.stream()
                .filter(transaction -> transaction.accountNumber.equals(accountNumber))
                .sorted(Comparator.reverseOrder())
                .limit(limit)
                .collect(Collectors.toList());
    }
}
