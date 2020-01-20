package com.app;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class Screen {

    private Account account;
    private String choice;
    private ActiveAccount activeAccount;
    private Account destinationAccount;

    private void setAccount(Account account) {
        this.account = account;
    }

    private Account getAccount() {
        return this.account;
    }

    private void setActiveAccount(ActiveAccount activeAccount) {
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

    Screen() {
        this.setAccount(new Account());
        this.setActiveAccount(new ActiveAccount());
    }

    void init() {
        this.getActiveAccount().setAccountsFromFile();

        this.showWelcomeScreen();
    }

    private void showWelcomeScreen() {
        Scanner inAccountNumber = new Scanner(System.in);
        System.out.print("Enter Account Number: ");
        this.getAccount().setAccountNumber(inAccountNumber.nextLine());

        if (Validation.validateInputLength(this.getAccount().getAccountNumber(), 6).getIsError()) {
            System.out.printf("Account Number should have %d digits length\n", 6);
            this.showWelcomeScreen();
        }

        if (Validation.validateInputIsNumeric(this.getAccount().getAccountNumber()).getIsError()) {
            System.out.println("Account number should only contains numbers");
            this.showWelcomeScreen();
        }

        Scanner inPin = new Scanner(System.in);
        System.out.print("Enter PIN: ");
        this.getAccount().setPin(inPin.nextLine());

        if (Validation.validateInputLength(this.getAccount().getPin(), 6).getIsError()) {
            System.out.printf("PIN should have %d digits length\n", 6);
            this.showWelcomeScreen();
        }

        if (Validation.validateInputIsNumeric(this.getAccount().getPin()).getIsError()) {
            System.out.println("PIN should only contains numbers");
            this.showWelcomeScreen();
        }

        String accountNumber = this.getAccount().getAccountNumber();
        String pin = this.getAccount().getPin();
        Validation validateAccountExistence = this.getActiveAccount().validateAccountExistence(accountNumber, pin);
        if (validateAccountExistence.getIsError()) {
            System.out.println(validateAccountExistence.getMessage());
        }

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
                    this.showHistoryScreen();
                    break;
                case "4":
                    this.showWelcomeScreen();
                    running = false;
                    break;
                case "":
                    this.showWelcomeScreen();
                    running = false;
                default:
                    this.showTransactionScreen();
                    break;
            }
        }
    }

    private void showTransactionMenu() {
        System.out.println("1. Withdraw");
        System.out.println("2. Fund Transfer");
        System.out.println("3. History");
        System.out.println("4. Exit");
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
                    this.getAccount().setWithdrawal(Account.DECREASE_TEN);
                    this.withdraw();
                    break;
                case "2":
                    this.getAccount().setWithdrawal(Account.DECREASE_FIFTY);
                    this.withdraw();
                    break;
                case "3":
                    this.getAccount().setWithdrawal(Account.DECREASE_HUNDRED);
                    this.withdraw();
                    break;
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

    private void withdraw() {
        if (this.getAccount().validateRemainingBalance().getIsError()) {
            System.out.println(this.getAccount().validateRemainingBalance().getMessage());
            this.showTransactionScreen();
        }

        this.getAccount().decreaseBalance();
        this.getAccount().saveWithdraw();
        this.showSummaryScreen();
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

        try {
            this.getAccount().setWithdrawal(in.nextInt());
        } catch (InputMismatchException e) {
            System.out.println("Amount should only contains numbers");
            this.showOtherWithdrawScreen();
        }

        Validation validateMaximumWithdraw = this.getAccount().validateMaximumWithdraw();
        if (validateMaximumWithdraw.getIsError()) {
            System.out.println(validateMaximumWithdraw.getMessage());
            this.showOtherWithdrawScreen();
        }

        Validation validateRemainingBalance = this.getAccount().validateRemainingBalance();
        if (validateRemainingBalance.getIsError()) {
            System.out.println(validateRemainingBalance.getMessage());
            this.showOtherWithdrawScreen();
        }

        Validation validateWithdrawAmountIsTenMultiply = this.getAccount().validateWithdrawAmountIsTenMultiply();
        if (validateWithdrawAmountIsTenMultiply.getIsError()) {
            System.out.println(validateWithdrawAmountIsTenMultiply.getMessage());
            this.showOtherWithdrawScreen();
        }

        this.getAccount().decreaseBalance();
        this.getAccount().saveWithdraw();
        this.showSummaryScreen();
    }

    private void showFundTransferScreen1() {
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter destination account and press enter to continue or \n" +
                "press enter to go back to Transaction: ");

        String accountNumber = in.nextLine();
        if (accountNumber.equals("")) {
            this.showTransactionScreen();
        }

        if (Validation.validateInputIsNumeric(this.getAccount().getAccountNumber()).getIsError()) {
            System.out.println("Account number should only contains numbers");
            this.showFundTransferScreen1();
        }

        Validation validateDestinationAccountNumber = this.getActiveAccount().validateDestinationAccountNumber(accountNumber);
        if (validateDestinationAccountNumber.getIsError()) {
            System.out.println(validateDestinationAccountNumber.getMessage());
            this.showFundTransferScreen1();
        }

        Account account = this.getActiveAccount().getDestinationAccount(accountNumber);
        if (account != null) {
            this.setDestinationAccount(account);
            this.showFundTransferScreen2();
        } else {
            this.showFundTransferScreen1();
        }
    }

    private void showFundTransferScreen2() {
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter transfer amount and \n" +
                "press enter to continue or \n" +
                "press enter to go back to Transaction: ");

        String amount = in.nextLine();

        if (amount.equals("")) {
            this.showTransactionScreen();
        }

        try {
            Integer iAmount = Integer.parseInt(amount);
            this.getAccount().setTransferAmount(iAmount);
            this.getAccount().setWithdrawal(iAmount);
        } catch (NumberFormatException e) {
            System.out.println("Transfer amount should only contains numbers");
            this.showFundTransferScreen2();
        }

        Validation validateMaximumTransfer = this.getAccount().validateMaximumTransfer();
        if (validateMaximumTransfer.getIsError()) {
            System.out.println(validateMaximumTransfer.getMessage());
            this.showFundTransferScreen2();
        }

        Validation validateMinimumTransfer = this.getAccount().validateMinimumTransfer();
        if (validateMinimumTransfer.getIsError()) {
            System.out.println(validateMinimumTransfer.getMessage());
            this.showFundTransferScreen2();
        }

        Validation validateRemainingBalance = this.getAccount().validateRemainingBalance();
        if (validateRemainingBalance.getIsError()) {
            System.out.println(validateRemainingBalance.getMessage());
            this.showFundTransferScreen2();
        }

        this.showFundTransferScreen3();
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
            this.getAccount().saveTransferFund(this.getDestinationAccount());
            this.showFundTransferSummaryScreen();
        } else {
            this.showFundTransferScreen1();
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

        if ("1".equals(this.getChoice())) {
            this.showTransactionScreen();
        } else if ("2".equals(this.getChoice())) {
            this.showWelcomeScreen();
        } else {
            this.showWelcomeScreen();
        }
    }

    private void showHistoryScreen() {
        List<Transaction> transactions = this.getAccount().getLastTenTransactions();

        if (transactions.isEmpty()) {
            System.out.println("No transactions");
        } else {
            this.showTransaction(transactions);

            this.showOtherHistoryScreen();
        }
    }

    private void showTransaction(List<Transaction> transactions) {
        Integer no = 1;
        for (Transaction transaction: transactions) {
            if (transaction.getType().equals(Withdraw.TYPE_WITHDRAW)) {
                System.out.printf("%d.%s %s-> amount: $%s\n", no, transaction.getDate(), transaction.getType(),
                        transaction.getAmount());
            } else {
                System.out.printf("%d.%s %s-> amount: $%s to: %s\n", no, transaction.getDate(), transaction.getType(),
                        transaction.getAmount(), transaction.getDestinationAccountNumber());
            }
            no++;
        }
    }

    private void showOtherHistoryScreen() {
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter an amount of last transactions or \n" +
                "press enter to go back to Transaction: ");
        String amount = in.nextLine();

        while(amount.equals("")) {
            this.showTransactionScreen();
        }

        if (Validation.validateInputIsNumeric(amount).getIsError()) {
            System.out.println("Input should only contains numbers");
            this.showOtherHistoryScreen();
        }

        Integer iAmount = Integer.parseInt(amount);

        List<Transaction> transactions = this.getAccount().getTransactions(iAmount);

        this.showTransaction(transactions);
    }
}