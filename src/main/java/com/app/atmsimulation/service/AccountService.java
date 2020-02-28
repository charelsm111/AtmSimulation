package com.app.atmsimulation.service;

import com.app.atmsimulation.model.Account;

public interface AccountService {

    public Account login(String accountNumber, String pin);

    public boolean withdraw(int amount);

    public void transfer(String destinationAccountNumber);
}
