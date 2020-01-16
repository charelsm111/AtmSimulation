package com.app;

import java.io.*;

class Withdraw extends Transaction {

    private static final String TYPE_WITHDRAW = "withdraw";

    Withdraw(Account account) {
        this.account = account;
    }

    @Override
    String getRecord() {
        this.setType(TYPE_WITHDRAW);

        return this.record =  this.getType() + "," +
                this.account.getAccountNumber() + "," +
                this.getDate() + "," +
                this.getAmount();
    }

}
