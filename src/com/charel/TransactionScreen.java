package com.charel;

import java.util.Scanner;

public class TransactionScreen {

    private Account account;
    private String choice;

    public TransactionScreen(Account account) {
        this.account = account;

        System.out.println("1. Witdraw");
        System.out.println("2. Fund Transfer");
        System.out.println("3. Exit");
        System.out.print("Please choose option[3]: ");
        Scanner choice = new Scanner(System.in);
        this.choice = choice.nextLine();
    }
}
