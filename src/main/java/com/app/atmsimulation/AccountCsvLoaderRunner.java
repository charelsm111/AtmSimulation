package com.app.atmsimulation;

import com.app.atmsimulation.model.Account;
import com.app.atmsimulation.service.AccountCsvServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class AccountCsvLoaderRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AccountCsvLoaderRunner.class);

    @Autowired
    private AccountCsvServiceImpl accountCsvService;

    @Override
    public void run(String... args) throws Exception {
        accountCsvService.readFile();
        for (Account account: accountCsvService.getAccountList()) {

            if (accountCsvService.findByAccountNumber(account.getAccountNumber()) == null) {
                accountCsvService.save(account);
            }
        }
    }
}
