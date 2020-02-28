package com.app.atmsimulation.repository;

import com.app.atmsimulation.model.Withdraw;
import org.springframework.data.repository.CrudRepository;

public interface WithdrawRepository extends CrudRepository<Withdraw, Long> {

    Withdraw save(Withdraw withdraw);
}
