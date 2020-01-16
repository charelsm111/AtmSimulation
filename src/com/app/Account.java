package com.app;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Random;

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

    void decreaseBalanceByTen() {

        this.balance = this.balance - DECREASE_TEN;
    }

    void decreaseBalanceByFifty() {

        this.balance = this.balance - DECREASE_FIFTY;
    }

    void decreaseBalanceByHundred() {

        this.balance = this.balance - DECREASE_HUNDRED;
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

    Validation validatePinIsNumeric() {
        Validation validation = new Validation();

        if (!this.pin.matches("[0-9]+")) {
            validation.setIsError();
            validation.setMessage("PIN should only contains numbers");
        }

        return validation;
    }

    Validation validateAccountNumberIsNumeric() {
        Validation validation = new Validation();

        if (!this.accountNumber.matches("[0-9]+")) {
            validation.setIsError();
            validation.setMessage("Account Number should only contains numbers");
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
        Withdraw withdraw = new Withdraw(this);

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm a");
        String formattedDateTime = localDateTime.format(dateTimeFormatter);

        withdraw.setDate(formattedDateTime);
        withdraw.setAmount(this.getWithdrawal());
        withdraw.saveToFile();
    }
}
