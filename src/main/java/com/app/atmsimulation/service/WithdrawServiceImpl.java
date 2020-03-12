package com.app.atmsimulation.service;

import com.app.atmsimulation.model.Account;
import com.app.atmsimulation.model.Withdraw;
import com.app.atmsimulation.repository.AccountRepository;
import com.app.atmsimulation.repository.WithdrawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class WithdrawServiceImpl implements WithdrawService {

    @Autowired
    private WithdrawRepository withdrawRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Withdraw save(Withdraw withdraw) {
        withdraw.setDate(LocalDate.now());
        Account account = withdraw.getAccount();
        Integer balance = account.getBalance() - withdraw.getAmount();

        account.setBalance(balance);
        accountRepository.save(account);

        return withdrawRepository.save(withdraw);
    }
}
