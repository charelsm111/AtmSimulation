package com.charel;

import java.util.Scanner;

public class WelcomeScreen extends Screen {

    private boolean loggedIn = false;

    public void show() {
        Scanner inAccountNumber = new Scanner(System.in);
        System.out.print("Enter Account Number: ");
        String accountNumber = inAccountNumber.nextLine();

        if (!Validation.validateLengthDigit(accountNumber, 6)) {
            System.out.println("Account Number should have 6 digits length");
            show();
        }

        if (!Validation.validateInputIsNumeric(accountNumber)) {
            System.out.println("Account Number should only contains numbers");
            show();
        }

        Scanner inPin = new Scanner(System.in);
        System.out.print("Enter PIN: ");
        String pin = inPin.nextLine();

        if (!Validation.validateLengthDigit(pin, 6)) {
            System.out.println("PIN should have 6 digits length");
            show();
        }

        if (!Validation.validateInputIsNumeric(pin)) {
            System.out.println("PIN should only contains numbers");
            show();
        }

        login(accountNumber, pin);

        if (loggedIn) {
            TransactionScreen transactionScreen = new TransactionScreen();
            transactionScreen.show();
        } else {
            System.out.println("Invalid Account Number/PIN");
            show();
        }
    }

    public void login(String accountNumber, String pin) {
        accountRepository.activeAccount = accountRepository.find(accountNumber, pin);

        if (accountRepository.activeAccount != null) {
            loggedIn = true;
        }
    }
}
