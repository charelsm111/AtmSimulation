package com.app.atmsimulation.service;

import com.app.atmsimulation.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface TransactionService {

    public List<Transaction> findByDateOrderByIdDesc(LocalDate date);
}
