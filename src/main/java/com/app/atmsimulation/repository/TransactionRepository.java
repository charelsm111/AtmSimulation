package com.app.atmsimulation.repository;

import com.app.atmsimulation.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT * FROM transaction ORDER BY id DESC LIMIT ?1", nativeQuery = true)
    List<Transaction> findLastTransaction(Integer amount);

    List<Transaction> findByDate(LocalDate date);
}
