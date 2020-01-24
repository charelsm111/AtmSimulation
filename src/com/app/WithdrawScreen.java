package com.charel;

import java.util.Scanner;

public class WithdrawScreen extends TransactionScreen {

    Withdraw withdraw = new Withdraw();

    WithdrawScreen() {
        withdraw.accountNumber = activeAccount.getAccountNumber();
        withdraw.date = getCurrentDateTime();
    }

    public void show() {
        System.out.println("1. $10");
        System.out.println("2. $50");
        System.out.println("3. $100");
        System.out.println("4. Other");
        System.out.println("5. Exit");
        System.out.print("Please choose option[5]: ");
        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();

        boolean running = true;

        while(running){
            TransactionScreen transactionScreen = new TransactionScreen();
            switch(choice){
                case "1":
                    if (activeAccount.validateRemainingBalance(10)) {
                        activeAccount.withdraw(10);
                        withdraw.amount = 10;
                        transactionRepository.add(withdraw);
                        showSummaryScreen();
                    } else {
                        System.out.println("Insufficient balance $" + 10);
                    }
                    break;
                case "2":
                    if (activeAccount.validateRemainingBalance(50)) {
                        activeAccount.withdraw(50);
                        withdraw.amount = 50;
                        transactionRepository.add(withdraw);
                        showSummaryScreen();
                    } else {
                        System.out.println("Insufficient balance $" + 50);
                    }
                    break;
                case "3":
                    if (activeAccount.validateRemainingBalance(50)) {
                        activeAccount.withdraw(100);
                        withdraw.amount = 100;
                        transactionRepository.add(withdraw);
                        showSummaryScreen();
                    } else {
                        System.out.println("Insufficient balance $" + 100);
                    }
                    break;
                case "4":
                    OtherWithdrawTransactionScreen otherWithdrawTransactionScreen = new OtherWithdrawTransactionScreen();
                    otherWithdrawTransactionScreen.show();
                    break;
                case "5":
                    transactionScreen.show();
                    running = false;
                    break;
                default:
                    transactionScreen.show();
                    break;
            }
        }
    }

    public void showSummaryScreen() {
        System.out.println("Summary");
        System.out.printf("Date: %s\n", withdraw.date);
        System.out.printf("Withdraw: %s\n", withdraw.amount);
        System.out.printf("Balance: %d\n", activeAccount.getBalance());
        System.out.println();

        System.out.println("1. Transaction");
        System.out.println("2. Exit");
        System.out.print("Please choose option[2]: ");
        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();

        boolean running = true;

        while(running) {
            TransactionScreen transactionScreen = new TransactionScreen();
            switch (choice) {
                case "2":
                    WelcomeScreen welcomeScreen = new WelcomeScreen();
                    welcomeScreen.show();
                    running = false;
                    break;
                default:
                    transactionScreen.show();
                    break;
            }
        }
    }
}
