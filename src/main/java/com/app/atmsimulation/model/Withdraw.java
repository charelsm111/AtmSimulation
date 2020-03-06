package com.app.atmsimulation.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.Date;

@Entity
@DiscriminatorValue("withdraw")
public class Withdraw extends Transaction {

    public Withdraw() {}
    
    public Withdraw(int amount, Account account) {
        this.setAmount(amount);
        this.setAccount(account);
    }

    public Withdraw(int amount, Account account, LocalDate date) {
        this.setAmount(amount);
        this.setAccount(account);
        this.setDate(date);
    }
}
