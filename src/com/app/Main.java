package com.app;

public class Main {

    public static void main(String[] args) {
        Account account = new Account();
        ActiveAccount activeAccount = new ActiveAccount();
        Screen screen = new Screen();
        screen.setAccount(account);
        screen.setActiveAccount(activeAccount);
        screen.showWelcomeScreen();
    }

}
