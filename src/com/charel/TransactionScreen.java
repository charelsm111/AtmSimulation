package com.charel;

import java.util.Scanner;

public class TransactionScreen {

    private Account account;

    public TransactionScreen(Account account) {
        this.account = account;
        boolean running = true;

        while(running){
            System.out.println("1. Witdraw");
            System.out.println("2. Fund Transfer");
            System.out.println("3. Exit");
            System.out.print("Please choose option[3]: ");
            Scanner choice = new Scanner(System.in);

            switch(choice.nextLine()){
                case "1":
                    System.out.println("Machine started!");
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
                    System.out.println("Command not recognized!");
                    break;
            }
        }
    }
}
