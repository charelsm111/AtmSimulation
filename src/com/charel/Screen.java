package com.charel;

import java.util.Scanner;

public class Screen {

    private Account account;
    private String choice;
    private ActiveAccount activeAccount;

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return this.account;
    }

    public void setActiveAccount(ActiveAccount activeAccount) {
        this.activeAccount = activeAccount;
    }

    public ActiveAccount getActiveAccount() {
        return this.activeAccount;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String getChoice() {
        return this.choice;
    }

    public void showWelcomeScreen() {
        Scanner inAccountNumber = new Scanner(System.in);
        System.out.print("Enter Account Number: ");
        this.getAccount().setAccountNumber(inAccountNumber.nextLine());

        Scanner inPin = new Scanner(System.in);
        System.out.print("Enter PIN: ");
        this.getAccount().setPin(inPin.nextLine());

        this.login();
    }

    public void login() {
        Account account = activeAccount.getAccount(this.getAccount().getAccountNumber(), this.getAccount().getPin());
        if (account != null) {
            this.setAccount(account);
            this.showTransactionScreen();
        } else {
            this.showWelcomeScreen();
        }
    }

    public void showTransactionScreen() {
        boolean running = true;

        while(running){
            this.showTransactionMenu();

            switch(this.choice){
                case "1":
                    this.showWithdrawScreen();
                    break;
                case "2":
                    break;
                case "3":
                    this.showWelcomeScreen();
                    running = false;
                    break;
                default:
                    this.showTransactionMenu();
                    break;
            }
        }
    }

    public void showTransactionMenu() {
        System.out.println("1. Withdraw");
        System.out.println("2. Fund Transfer");
        System.out.println("3. Exit");
        System.out.print("Please choose option[3]: ");
        Scanner in = new Scanner(System.in);
        this.setChoice(in.nextLine());
    }

    public void showWithdrawScreen() {
        boolean running = true;

        while(running){
            this.showWithdrawMenu();

            switch(this.getChoice()){
                case "1":
                    if (this.getAccount().decreaseBalanceByTen()) {
                        this.showSummaryScreen();
                    }
                    break;
                case "2":
                    if (this.account.decreaseBalanceByFifty()) {
                        this.showSummaryScreen();
                    }
                    break;
                case "3":
                    if (this.account.decreaseBalanceByHundred()) {
                        this.showSummaryScreen();
                    }
                case "4":
                    this.showOtherWithdrawScreen();
                    break;
                case "5":
                    this.showTransactionScreen();
                    running = false;
                    break;
                default:
                    this.showTransactionScreen();
                    break;
            }
        }
    }

    public void showWithdrawMenu() {
        System.out.println("1. $10");
        System.out.println("2. $50");
        System.out.println("3. $100");
        System.out.println("4. Other");
        System.out.println("5. Exit");
        System.out.print("Please choose option[5]: ");
        Scanner in = new Scanner(System.in);
        this.setChoice(in.nextLine());
    }

    public void showSummaryScreen() {
        boolean running = true;

        while(running){
            this.showSummary();

            this.showSummaryMenu();
            switch(this.getChoice()){
                case "1":
                    this.showTransactionScreen();
                    break;
                case "2":
                    this.showWelcomeScreen();
                    running = false;
                    break;
                default:
                    this.showTransactionScreen();
                    break;
            }
        }
    }

    public void showSummaryMenu() {
        System.out.println("1. Transaction");
        System.out.println("2. Exit");
        System.out.print("Please choose option[2]: ");
        Scanner in = new Scanner(System.in);
        this.setChoice(in.nextLine());
    }

    public void showSummary() {
        System.out.println("Summary");
        // TODO: Add date API
        System.out.printf("Date: \n");
        System.out.printf("Withdraw: %d\n", this.getAccount().getWithdrawal());
        System.out.printf("Balance: %d\n", this.getAccount().getBalance());
        System.out.println("");
    }

    public void showOtherWithdrawScreen() {
        System.out.println("Other Withdraw");
        System.out.print("Enter Amount to Withdraw: ");
        Scanner in = new Scanner(System.in);
        String amount = in.nextLine();

        if (this.getAccount().decreaseBalance(amount)) {
            this.showSummaryScreen();
        }
    }

    public void showFundTransferScreen() {

    }


}
