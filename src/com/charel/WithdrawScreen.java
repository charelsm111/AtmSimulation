package com.charel;

import java.util.Scanner;

// TODO: Should be have a Super Class
public class WithdrawScreen {

    private Account account;
    private String choice;

    public WithdrawScreen(Account account) {
        this.account = account;
    }

    public void show() {
        boolean running = true;

        while(running){
            this.showMenu();
            TransactionScreen transactionScreen = new TransactionScreen(this.account);
            SummaryScreen summaryScreen = new SummaryScreen(this.account);
            switch(this.choice){
                case "1":
                    if (this.account.decreaseBalanceByTen()) {
                        summaryScreen.show();
                    }
                    break;
                case "2":
                    if (this.account.decreaseBalanceByFifty()) {
                        summaryScreen.show();
                    }
                    break;
                case "3":
                    if (this.account.decreaseBalanceByHundred()) {
                        summaryScreen.show();
                    }
                case "4":
                    OtherWithdrawScreen otherWithdrawScreen = new OtherWithdrawScreen(this.account);
                    otherWithdrawScreen.show();
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

    public void showMenu() {
        System.out.println("1. $10");
        System.out.println("2. $50");
        System.out.println("3. $100");
        System.out.println("4. Other");
        System.out.println("5. Exit");
        System.out.print("Please choose option[5]: ");
        Scanner in = new Scanner(System.in);
        this.choice = in.nextLine();
    }
}
