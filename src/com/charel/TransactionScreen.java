package com.charel;

import java.util.Scanner;

public class TransactionScreen {

    private Account account;
    private String choice;

    public TransactionScreen(Account account) {
        this.account = account;
    }

    public void show() {
        boolean running = true;

        while(running){
            this.showMenu();

            switch(this.choice){
                case "1":
                    WithdrawScreen withdrawScreen = new WithdrawScreen(this.account);
                    withdrawScreen.show();
                    break;
                case "2":
                    System.out.println("Machine stopped.");
                    break;
                case "3":
                    WelcomeScreen welcomeScreen = new WelcomeScreen();
                    welcomeScreen.show();
                    running = false;
                    break;
                default:
                    this.showMenu();
                    break;
            }
        }
    }

    public void showMenu() {
        System.out.println("1. Witdraw");
        System.out.println("2. Fund Transfer");
        System.out.println("3. Exit");
        System.out.print("Please choose option[3]: ");
        Scanner in = new Scanner(System.in);
        this.choice = in.nextLine();
    }

}
