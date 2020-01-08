package com.charel;

import java.util.Scanner;

public class WelcomeScreen {

    private String accountNumber;
    private String pin;
    private ActiveAccount activeAccount;

    public void show() {
        Scanner inAccountNumber = new Scanner(System.in);
        System.out.print("Enter Account Number: ");
        this.accountNumber = inAccountNumber.nextLine();

        Scanner inPin = new Scanner(System.in);
        System.out.print("Enter PIN: ");
        this.pin = inPin.nextLine();

        this.activeAccount = new ActiveAccount();

        this.login();
    }

    public void login() {
        Account account = activeAccount.getAccount(accountNumber, pin);
        if (account != null) {
            TransactionScreen transactionScreen = new TransactionScreen(account);
        } else {
            this.show();
        }
    }

}
