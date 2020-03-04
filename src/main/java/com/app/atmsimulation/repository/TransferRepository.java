package com.app.atmsimulation.repository;

import com.app.atmsimulation.model.Transfer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends CrudRepository<Transfer, Long> {

    Transfer save(Transfer transfer);
}
