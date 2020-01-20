package com.app;

class FundTransfer extends Transaction {

    static final String TYPE_TRANSFER = "transfer";

    FundTransfer() {
        this.setType(TYPE_TRANSFER);
    }
}
