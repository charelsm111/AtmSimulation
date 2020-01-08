package com.charel;

import java.util.Scanner;

public class OtherWithdrawScreen {

    private Account account;

    public OtherWithdrawScreen(Account account) {
        this.account = account;
    }

    public void show() {
        System.out.println("Other Withdraw");
        System.out.print("Enter Amount to Withdraw: ");
        Scanner in = new Scanner(System.in);
        String amount = in.nextLine();

        if (this.account.decreaseBalance(amount)) {
            SummaryScreen summaryScreen = new SummaryScreen(this.account);
            summaryScreen.show();
        }
    }
}
