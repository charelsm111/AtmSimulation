package com.charel;

import java.util.Scanner;

public class OtherWithdrawTransactionScreen extends WithdrawScreen {

    public void show() {
        System.out.println("Other Withdraw");
        System.out.print("Enter Amount to Withdraw: ");
        Scanner in = new Scanner(System.in);
        String answer = in.nextLine();
        Integer amount = Integer.parseInt(answer);

        if (!Validation.validateMaximumDraw(amount)) {
            System.out.println("Maximum amount to withdraw is $1000");
            show();
        }

        if (!Validation.validateInputIsNumeric(answer)) {
            System.out.println("Invalid amount");
            show();
        }

        if (!Validation.validateInputIsTenMultiplied(amount)) {
            System.out.println("Invalid amount");
            show();
        }

        if (!activeAccount.validateRemainingBalance(amount)) {
            System.out.println("Insufficient balance $" + amount);
            show();
        }

        withdraw(amount);
        transactionRepository.add(withdraw);
        showSummaryScreen();
    }

    private void withdraw(Integer amount) {
        activeAccount.withdraw(amount);
        withdraw.amount = amount;
    }
}
