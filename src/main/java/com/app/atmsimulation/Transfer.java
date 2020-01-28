package com.app.atmsimulation;

import java.util.Random;

public class Transfer extends Transaction {

    String referenceNumber;

    Transfer() {
        referenceNumber = generateReferenceNumber();
        setType();
    }

    @Override
    public void setType() {
        type = "transfer";
    }

    private String generateReferenceNumber() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }

}
