package com.app;

class Withdraw extends Transaction {

    static final String TYPE_WITHDRAW = "withdraw";

    Withdraw() {
        this.setType(TYPE_WITHDRAW);
    }

}
