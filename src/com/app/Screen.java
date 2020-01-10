package com.app;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

class Screen {

    private Account account;
    private String choice;
    private ActiveAccount activeAccount;
    private Account destinationAccount;

    void setAccount(Account account) {
        this.account = account;
    }

    private Account getAccount() {
        return this.account;
    }

    void setActiveAccount(ActiveAccount activeAccount) {
        this.activeAccount = activeAccount;
    }

    private ActiveAccount getActiveAccount() {
        return this.activeAccount;
    }

    private void setChoice(String choice) {
        this.choice = choice;
    }

    private String getChoice() {
        return this.choice;
    }

    private void setDestinationAccount(Account destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    private Account getDestinationAccount() {
        return this.destinationAccount;
    }

    void showWelcomeScreen() {
        Scanner inAccountNumber = new Scanner(System.in);
        System.out.print("Enter Account Number: ");
        this.getAccount().setAccountNumber(inAccountNumber.nextLine());

        Scanner inPin = new Scanner(System.in);
        System.out.print("Enter PIN: ");
        this.getAccount().setPin(inPin.nextLine());

        this.login();
    }

    private void login() {
        Account account = activeAccount.getAccount(this.getAccount().getAccountNumber(), this.getAccount().getPin());
        if (account != null) {
            this.setAccount(account);
            this.showTransactionScreen();
        } else {
            this.showWelcomeScreen();
        }
    }

    private void showTransactionScreen() {
        boolean running = true;

        while(running){
            this.showTransactionMenu();

            switch(this.choice){
                case "1":
                    this.showWithdrawScreen();
                    break;
                case "2":
                    this.showFundTransferScreen1();
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

    private void showTransactionMenu() {
        System.out.println("1. Withdraw");
        System.out.println("2. Fund Transfer");
        System.out.println("3. Exit");
        System.out.print("Please choose option[3]: ");
        Scanner in = new Scanner(System.in);
        this.setChoice(in.nextLine());
    }

    private void showWithdrawScreen() {
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

    private void showWithdrawMenu() {
        System.out.println("1. $10");
        System.out.println("2. $50");
        System.out.println("3. $100");
        System.out.println("4. Other");
        System.out.println("5. Exit");
        System.out.print("Please choose option[5]: ");
        Scanner in = new Scanner(System.in);
        this.setChoice(in.nextLine());
    }

    private void showSummaryScreen() {
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
                    this.showFundTransferScreen1();
                    break;
            }
        }
    }

    private void showSummaryMenu() {
        System.out.println("1. Transaction");
        System.out.println("2. Exit");
        System.out.print("Please choose option[2]: ");
        Scanner in = new Scanner(System.in);
        this.setChoice(in.nextLine());
    }

    private void showSummary() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm a");
        String formattedDateTime = localDateTime.format(dateTimeFormatter);

        System.out.println("Summary");
        System.out.printf("Date: %s\n", formattedDateTime);
        System.out.printf("Withdraw: %d\n", this.getAccount().getWithdrawal());
        System.out.printf("Balance: %d\n", this.getAccount().getBalance());
        System.out.println();
    }

    private void showOtherWithdrawScreen() {
        System.out.println("Other Withdraw");
        System.out.print("Enter Amount to Withdraw: ");
        Scanner in = new Scanner(System.in);
        String amount = in.nextLine();

        if (this.getAccount().decreaseBalance(amount)) {
            this.showSummaryScreen();
        }
    }

    private void showFundTransferScreen1() {
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter destination account and press enter to continue or \n" +
                "press enter to go back to Transaction: ");

        Account account = this.getActiveAccount().getDestinationAccount(in.nextLine());
        if (account != null) {
            this.setDestinationAccount(account);
            this.showFundTransferScreen2();
        } else {
            this.showTransactionScreen();
        }
    }

    private void showFundTransferScreen2() {
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter transfer amount and \n" +
                "press enter to continue or \n" +
                "press enter to go back to Transaction: ");

        String amount = in.nextLine();

        while (!amount.equals("")) {
            // TODO: It should be static method
            Validation validateTransferAmountIsNumeric = this.getAccount().validateTransferAmountIsNumeric(amount);
            if (validateTransferAmountIsNumeric.getIsError()) {
                System.out.println(validateTransferAmountIsNumeric.getMessage());
                this.showTransactionScreen();
            }

            Integer iAmount = Integer.parseInt(amount);
            this.getAccount().setTransferAmount(iAmount);

            Validation validateMaximumTransfer = this.getAccount().validateMaximumTransfer();
            if (validateMaximumTransfer.getIsError()) {
                System.out.println(validateMaximumTransfer.getMessage());
                this.showTransactionScreen();
            }

            Validation validateMinimumTransfer = this.getAccount().validateMinimumTransfer();
            if (validateMinimumTransfer.getIsError()) {
                System.out.println(validateMinimumTransfer.getMessage());
                this.showTransactionScreen();
            }

            Validation validateRemainingBalance = this.getAccount().validateRemainingBalance(iAmount);
            if (validateRemainingBalance.getIsError()) {
                System.out.println(validateRemainingBalance.getMessage());
                this.showTransactionScreen();
            }

            this.showFundTransferScreen3();
            amount = "exit";
        }

        this.showTransactionScreen();
    }

    private void showFundTransferScreen3() {
        this.getAccount().setReferenceNumber(this.getAccount().generateReferenceNumber());

        System.out.printf("Reference Number: %s\n" +
                "press enter to continue ", this.getAccount().getReferenceNumber());

        this.showFundTransferScreen4();
    }

    private void showFundTransferScreen4() {
        System.out.println("Transfer Confirmation");
        System.out.printf("Destination Account : %s\n", this.getDestinationAccount().getAccountNumber());
        System.out.printf("Transfer Amount : %d\n", this.getAccount().getTransferAmount());
        System.out.printf("Reference Number : %s\n\n", this.getAccount().getReferenceNumber());

        System.out.println("1. Confirm Trx");
        System.out.println("2. Cancel Trx");
        System.out.print("Choose option[2]: ");
        Scanner in = new Scanner(System.in);
        this.setChoice(in.nextLine());

        if ("1".equals(this.getChoice())) {
            this.getAccount().transferFund(this.getDestinationAccount());
            this.showFundTransferSummaryScreen();
        } else {
            this.showTransactionScreen();
        }
    }

    private void showFundTransferSummaryScreen() {
        System.out.println("Fund Transfer Summary");
        System.out.printf("Destination Account : %s\n", this.getDestinationAccount().getAccountNumber());
        System.out.printf("Transfer Amount : %d\n", this.getAccount().getTransferAmount());
        System.out.printf("Reference Number : %s\n", this.getAccount().getReferenceNumber());
        System.out.printf("Balance : %s\n\n", this.getAccount().getBalance());

        System.out.println("1. Transaction");
        System.out.println("2. Exit");
        System.out.print("Choose option[2]: ");
        Scanner in = new Scanner(System.in);
        this.setChoice(in.nextLine());

        if ("2".equals(this.getChoice())) {
            this.showWelcomeScreen();
        } else {
            this.showTransactionScreen();
        }
    }

}
