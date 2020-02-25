package com.app.atmsimulation.repository;

import com.app.atmsimulation.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByAccountNumberAndPin(String accountNumber, String pin);

    Account findByAccountNumber(String accountNumber);
}
