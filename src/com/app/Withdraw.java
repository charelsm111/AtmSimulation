package com.app;

class Withdraw extends Transaction {

    static final String TYPE_WITHDRAW = "withdraw";

    @Override
    String getRecord() {
        this.setType(TYPE_WITHDRAW);

        return this.record =  this.getType() + "," +
                this.getAccountNumber() + "," +
                this.getDate() + "," +
                this.getAmount();
    }

}
