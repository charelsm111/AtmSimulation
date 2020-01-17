package com.app;

class FundTransfer extends Transaction {

    static final String TYPE_TRANSFER = "transfer";

    @Override
    String getRecord() {
        this.setType(TYPE_TRANSFER);

        return this.record =  this.getType() + "," +
                this.getAccountNumber() + "," +
                this.getDate() + "," +
                this.getAmount() + "," +
                this.getDestinationAccountNumber();
    }
}
