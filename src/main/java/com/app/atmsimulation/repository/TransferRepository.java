package com.app.atmsimulation.repository;

import com.app.atmsimulation.model.Transfer;
import org.springframework.data.repository.CrudRepository;

public interface TransferRepository extends CrudRepository<Transfer, Long> {

    Transfer save(Transfer transfer);
}
