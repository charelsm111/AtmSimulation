package com.charel;

import java.util.List;
import java.util.Scanner;

public class WelcomeScreen {

    private String accountNumber;
    private String pin;
    private ActiveAccount activeAccount;
    private boolean isLoggedIn;

    public WelcomeScreen(ActiveAccount activeAccount) {
        Scanner inAccountNumber = new Scanner(System.in);
        System.out.print("Enter Account Number: ");
        this.accountNumber = inAccountNumber.nextLine();

        Scanner inPin = new Scanner(System.in);
        System.out.print("Enter PIN: ");
        this.pin = inPin.nextLine();

        this.activeAccount = activeAccount;

        this.login();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public void login() {
        Account account = activeAccount.getAccount(accountNumber, pin);
        if (account != null) {
            TransactionScreen transactionScreen = new TransactionScreen(account);
        }
    }

    public boolean getIsLoggedIn() {
        return isLoggedIn;
    }
}
