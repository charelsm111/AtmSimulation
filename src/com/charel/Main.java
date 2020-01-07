package com.charel;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account("John Doe", "012108", 100, "112233"));
        accounts.add(new Account("Jane Doe", "932012", 30, "112244"));

        ActiveAccount activeAccount = new ActiveAccount();
        activeAccount.setAccounts(accounts);

        WelcomeScreen welcomeScreen = new WelcomeScreen(activeAccount);
        welcomeScreen.login();
    }

}
