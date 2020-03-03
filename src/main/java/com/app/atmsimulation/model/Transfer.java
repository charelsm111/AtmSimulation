package com.app.atmsimulation.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("transfer")
public class Transfer extends Transaction {

    @Column
    private String destinationAccountNumber;

    public Transfer() {}

    public Transfer(int amount, Account account, String destinationAccountNumber) {
        this.setAmount(amount);
        this.setAccount(account);
        this.destinationAccountNumber = destinationAccountNumber;
    }

    public void setDestinationAccountNumber(String destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
    }

    public String getDestinationAccountNumber() {
        return destinationAccountNumber;
    }
}
