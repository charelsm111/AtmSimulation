package com.app.atmsimulation.service;

import com.app.atmsimulation.model.Transaction;
import com.app.atmsimulation.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> findByDateAndAccountIdOrderByIdDesc(LocalDate date, Long accountId) {

        return transactionRepository.findByDateAndAccountIdOrderByIdDesc(date, accountId);
    }
}
