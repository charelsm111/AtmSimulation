package com.app.atmsimulation.repository;

import com.app.atmsimulation.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByDateAndAccountIdOrderByIdDesc(LocalDate date, Long accountId);
}
