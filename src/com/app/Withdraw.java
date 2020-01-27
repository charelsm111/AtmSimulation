package com.app;

public class Withdraw extends Transaction {

    Withdraw() {
        setType();
    }

    @Override
    public void setType() {
        type = "withdraw";
    }

}
