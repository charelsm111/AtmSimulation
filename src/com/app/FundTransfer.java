package com.app;

public class FundTransfer extends Transaction {

    private static final String TYPE_TRANSFER = "transfer";

    String destinationAccountNumber;

    FundTransfer(Account account) {
        this.account = account;
    }

    void setDestinationAccountNumber(String destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
    }

    String getDestinationAccountNumber() {
        return this.destinationAccountNumber;
    }

    @Override
    String getRecord() {
        this.setType(TYPE_TRANSFER);

        return this.record =  this.getType() + "," +
                this.account.getAccountNumber() + "," +
                this.getDate() + "," +
                this.getAmount() + "," +
                this.getDestinationAccountNumber();
    }
}
