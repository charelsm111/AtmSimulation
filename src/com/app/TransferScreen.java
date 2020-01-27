package com.app;

import java.util.Scanner;

public class TransferScreen extends TransactionScreen {

    Transfer transfer = new Transfer();

    TransferScreen() {
        transfer.accountNumber = activeAccount.getAccountNumber();
        transfer.date = getCurrentDateTime();
    }

    public void show() {
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter destination account and press enter to continue or \n" +
                "press enter to go back to Transaction: ");
        String accountNumber = in.nextLine();

        if (!Validation.validateInputIsNumeric(accountNumber)) {
            System.out.println("Invalid account");
            show();
        }

        if (accountRepository.find(accountNumber) == null) {
            System.out.println("Invalid account");
            show();
        }

        transfer.destinationAccountNumber = accountRepository.find(accountNumber).getAccountNumber();
        showTransferAmountScreen();
    }

    private void showTransferAmountScreen() {
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter transfer amount and \n" +
                "press enter to continue or \n" +
                "press enter to go back to Transaction: ");

        String amount = in.nextLine();

        if (!Validation.validateInputIsNumeric(amount)) {
            System.out.println("Invalid amount");
            show();
        }

        transfer.amount = Integer.parseInt(amount);

        if (!Validation.validateMaximumDraw(transfer.amount)) {
            System.out.println("Maximum amount to withdraw is $1000");
            show();
        }

        if (!Validation.validateMinimumDraw(transfer.amount)) {
            System.out.println("Minimum amount to withdraw is $1");
            show();
        }


        showReferenceNumberScreen();
    }

    private void showReferenceNumberScreen() {
        Scanner in = new Scanner(System.in);
        System.out.printf("Reference Number: %s\n" +
                "press enter to continue ", transfer.referenceNumber);
        String answer = in.nextLine();

        this.showConfirmationScreen();
    }

    private void showConfirmationScreen() {
        System.out.println("Transfer Confirmation");
        System.out.printf("Destination Account : %s\n", transfer.destinationAccountNumber);
        System.out.printf("Transfer Amount : %d\n", transfer.amount);
        System.out.printf("Reference Number : %s\n\n", transfer.referenceNumber);

        System.out.println("1. Confirm Trx");
        System.out.println("2. Cancel Trx");
        System.out.print("Choose option[2]: ");
        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();

        if ("1".equals(choice)) {
            activeAccount.transfer(transfer.destinationAccountNumber, transfer.amount);
            transactionRepository.add(transfer);
            showSummaryScreen();
        } else {
            show();
        }
    }

    private void showSummaryScreen() {
        System.out.println("Fund Transfer Summary");
        System.out.printf("Destination Account : %s\n", transfer.destinationAccountNumber);
        System.out.printf("Transfer Amount : %d\n", transfer.amount);
        System.out.printf("Reference Number : %s\n", transfer.referenceNumber);
        System.out.printf("Balance : %s\n\n", activeAccount.getBalance());

        System.out.println("1. Transaction");
        System.out.println("2. Exit");
        System.out.print("Choose option[2]: ");
        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();

        WelcomeScreen welcomeScreen = new WelcomeScreen();
        if ("1".equals(choice)) {
            TransactionScreen transactionScreen = new TransactionScreen();
            transactionScreen.show();
        } else if ("2".equals(choice)) {
            welcomeScreen.show();
        } else {
            welcomeScreen.show();
        }
    }
}
