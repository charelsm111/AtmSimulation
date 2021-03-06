package com.app.atmsimulation.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("transfer")
public class Transfer extends Transaction {

    public Transfer() {}

    public Transfer(int amount, Account account, String destinationAccountNumber) {
        this.setAmount(amount);
        this.setAccount(account);
        this.setDestinationAccountNumber(destinationAccountNumber);
    }

    public Transfer(int amount, Account account, String destinationAccountNumber, LocalDate date) {
        this.setAmount(amount);
        this.setAccount(account);
        this.setDestinationAccountNumber(destinationAccountNumber);
        this.setDate(date);
    }
}
