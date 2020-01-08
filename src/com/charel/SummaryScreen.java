package com.charel;

import java.util.Scanner;

// TODO: Should be have a Super Class
public class SummaryScreen {

    private Account account;
    private String choice;

    public SummaryScreen(Account account) {
        this.account = account;
    }

    public void showMenu() {
        System.out.println("1. Transaction");
        System.out.println("2. Exit");
        System.out.print("Please choose option[2]: ");
        Scanner in = new Scanner(System.in);
        this.choice = in.nextLine();
    }

    public void show() {
        boolean running = true;

        while(running){
            this.showSummary();

            this.showMenu();
            TransactionScreen transactionScreen = new TransactionScreen(this.account);
            WelcomeScreen welcomeScreen = new WelcomeScreen();
            switch(this.choice){
                case "1":
                    transactionScreen.show();
                    break;
                case "2":
                    welcomeScreen.show();
                    running = false;
                    break;
                default:
                    transactionScreen.show();
                    break;
            }
        }
    }

    public void showSummary() {
        System.out.println("Summary");
        System.out.printf("Date: \n");
        System.out.printf("Withdraw: %d\n", this.account.getWithdrawal());
        System.out.printf("Balance: %d\n", this.account.getBalance());
        System.out.println("");
    }
}
