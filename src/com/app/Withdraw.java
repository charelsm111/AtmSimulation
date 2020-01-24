package com.charel;

public class Withdraw extends Transaction {

    Withdraw() {
        setType();
    }

    @Override
    public void setType() {
        type = "withdraw";
    }

}
