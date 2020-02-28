package com.app.atmsimulation.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("transfer")
public class Transfer extends Transaction {

    @Column
    private String destinationAccountNumber;
}