package com.app.atmsimulation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Screen {

    Account activeAccount; /* an logged in account*/
    AccountRepository accountRepository;

    Screen() {
        accountRepository = AccountRepository.getInstance();
        activeAccount = accountRepository.activeAccount;
    }

    void show(){}

    public String getCurrentDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");
        return localDateTime.format(dateTimeFormatter);
    }
}
