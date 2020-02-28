package com.app.atmsimulation.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("withdraw")
public class Withdraw extends Transaction {

}
