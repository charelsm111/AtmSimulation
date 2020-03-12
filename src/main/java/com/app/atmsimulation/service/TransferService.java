package com.app.atmsimulation.service;

import com.app.atmsimulation.model.Transfer;
import org.springframework.stereotype.Service;

@Service
public interface TransferService {

    public Transfer save(Transfer transfer);
}
