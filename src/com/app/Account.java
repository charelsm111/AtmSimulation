package com.app;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

class Account {

    static final Integer DECREASE_TEN = 10;
    static final Integer DECREASE_FIFTY = 50;
    static final Integer DECREASE_HUNDRED = 100;
    private static final Integer MAX_WITHDRAWAL_AMOUNT = 1000;
    private static final Integer MAX_TRANSFER_AMOUNT = 1000;
    private static final Integer MIN_TRANSFER_AMOUNT = 1;

    private String name;
    private String pin;
    private Integer balance;
    private String accountNumber;
    private Integer withdrawal;
    private Integer transferAmount;
    private String referenceNumber;

    void setName(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    void setPin(String pin) {
        this.pin = pin;
    }

    String getPin() {
        return pin;
    }

    void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    String getAccountNumber() {
        return accountNumber;
    }

    void setBalance(Integer balance) {
        this.balance = balance;
    }

    Integer getBalance() { return balance; }

    void setTransferAmount(Integer transferAmount) {
        this.transferAmount = transferAmount;
    }

    Integer getTransferAmount() {
        return this.transferAmount;
    }

    void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    String getReferenceNumber() {
        return this.referenceNumber;
    }

    Integer getWithdrawal() { return withdrawal; }

    void setWithdrawal(Integer withdrawal) {
        this.withdrawal = withdrawal;
    }

    void decreaseBalance() {

        this.balance = this.balance - this.withdrawal;
    }

    Validation validateRemainingBalance() {
        Validation validation = new Validation();
        if (this.balance < this.withdrawal) {
            validation.setIsError();
            validation.setMessage("Insufficient Balance $" + this.withdrawal);
        }

        return validation;
    }

    Validation validateMaximumWithdraw() {
        Validation validation = new Validation();
        if (this.withdrawal > MAX_WITHDRAWAL_AMOUNT) {
            validation.setIsError();
            validation.setMessage("Maximum amount to withdraw is $" + MAX_WITHDRAWAL_AMOUNT);
        }

        return validation;
    }

    Validation validateWithdrawAmountIsTenMultiply() {
        Validation validation = new Validation();
        if (this.withdrawal % 10 != 0) {
            validation.setIsError();
            validation.setMessage("Invalid amount");
        }

        return validation;
    }

    Validation validateMaximumTransfer() {
        Validation validation = new Validation();
        if (this.getTransferAmount() > MAX_TRANSFER_AMOUNT) {
            validation.setIsError();
            validation.setMessage("Maximum amount to withdraw is $" + MAX_TRANSFER_AMOUNT);
        }

        return validation;
    }

    Validation validateMinimumTransfer() {
        Validation validation = new Validation();
        if (this.getTransferAmount() < MIN_TRANSFER_AMOUNT) {
            validation.setIsError();
            validation.setMessage("Minimum amount to withdraw is $" + MIN_TRANSFER_AMOUNT);
        }

        return validation;
    }

    String generateReferenceNumber() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }

    void transferFund(Account destinationAccount) {
        destinationAccount.balance = destinationAccount.balance + this.transferAmount;
        this.balance = this.balance - this.transferAmount;
    }

    Validation validateAccountNumberLength() {
        Validation validation = new Validation();

        if (this.accountNumber.length() < 6) {
            validation.setIsError();
            validation.setMessage("Account Number should have 6 digits length");
        }

        return validation;
    }

    Validation validatePinLength() {
        Validation validation = new Validation();

        if (this.pin.length() < 6) {
            validation.setIsError();
            validation.setMessage("PIN should have 6 digits length");
        }

        return validation;
    }

    /*
    * Used to compare the equality of two objects
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountNumber.equals(account.getAccountNumber());
    }

    /*
    * Collection use a hashcode value of an object to determine
    * how it should be stored inside a collection. Must be overridden
    * in every class which override equals() method
     */
    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }

    void saveWithdraw() {
        Withdraw withdraw = new Withdraw();

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");
        String formattedDateTime = localDateTime.format(dateTimeFormatter);

        withdraw.setAccountNumber(this.getAccountNumber());
        withdraw.setDate(formattedDateTime);
        withdraw.setAmount(this.getWithdrawal());
        withdraw.saveToFile();
    }

    void saveTransferFund(Account destinationAccount) {
        FundTransfer fundTransfer = new FundTransfer();

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");
        String formattedDateTime = localDateTime.format(dateTimeFormatter);

        fundTransfer.setAccountNumber(this.getAccountNumber());
        fundTransfer.setDate(formattedDateTime);
        fundTransfer.setAmount(this.getWithdrawal());
        fundTransfer.setDestinationAccountNumber(destinationAccount.getAccountNumber());
        fundTransfer.saveToFile();
    }

    List<Transaction> getLastTenTransactions() {
        Transaction transaction = new Transaction();
        List<Transaction> transactions = transaction.getAllTransactionsFromFile();

        return transactions.stream()
                .filter(transaction1 -> transaction1.getAccountNumber().equals(this.getAccountNumber()))
                .sorted(Comparator.reverseOrder())
                .limit(10)
                .collect(Collectors.toList());
    }

    List<Transaction> getTransactions(Integer amount) {
        Transaction transaction = new Transaction();
        List<Transaction> transactions = transaction.getAllTransactionsFromFile();

        return transactions.stream()
                .filter(transaction1 -> transaction1.getAccountNumber().equals(this.getAccountNumber()))
                .sorted(Comparator.reverseOrder())
                .limit(amount)
                .collect(Collectors.toList());
    }

}
