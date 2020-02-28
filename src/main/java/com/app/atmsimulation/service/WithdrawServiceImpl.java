package com.app.atmsimulation.service;

import com.app.atmsimulation.model.Withdraw;
import com.app.atmsimulation.repository.WithdrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WithdrawServiceImpl implements WithdrawService {

    @Autowired
    private WithdrawRepository withdrawRepository;

    @Override
    public Withdraw save(Withdraw withdraw) {

        return withdrawRepository.save(withdraw);
    }
}
