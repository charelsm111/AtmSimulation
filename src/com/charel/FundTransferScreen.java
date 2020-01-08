package com.charel;

import java.util.Scanner;

// TODO: Should be have a Super Class
public class FundTransferScreen {

    private Account sourceAccount;
    private ActiveAccount activeAccount;
    private Account destinationAccount;

    public FundTransferScreen(Account account) {
        this.sourceAccount = account;
        this.activeAccount = new ActiveAccount();
    }

    public void show() {
        Scanner inAccountNumber = new Scanner(System.in);
        System.out.print("Please enter destination account and press enter to continue or \n" +
                "press enter to go back to Transaction: ");
        String destinationAccountNumber = inAccountNumber.nextLine();

        this.destinationAccount = activeAccount.getDestinationAccount(destinationAccountNumber);
        System.out.printf("Account Name: %s\n", this.destinationAccount.getName());
    }

}
