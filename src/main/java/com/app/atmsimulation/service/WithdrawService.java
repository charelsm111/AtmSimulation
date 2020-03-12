package com.app.atmsimulation.service;

import com.app.atmsimulation.model.Withdraw;
import org.springframework.stereotype.Service;

@Service
public interface WithdrawService {

    public Withdraw save(Withdraw withdraw);
}
