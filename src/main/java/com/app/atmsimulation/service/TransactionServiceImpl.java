package com.app.atmsimulation.service;

import com.app.atmsimulation.model.Transaction;
import com.app.atmsimulation.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> findLastTransaction(Integer amount) {

        return transactionRepository.findLastTransaction(amount);
    }
}
