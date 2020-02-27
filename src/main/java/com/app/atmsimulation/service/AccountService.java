package com.app.atmsimulation.service;

import com.app.atmsimulation.model.Account;

public interface AccountService {

    public Account login(String accountNumber, String pin);

    public void withdraw();

    public void transfer(String destinationAccountNumber);
}
