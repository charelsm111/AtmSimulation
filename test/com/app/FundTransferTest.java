package com.app;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FundTransferTest {

    @Test
    void testGetRecord() {
        FundTransfer fundTransfer = new FundTransfer();

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm a");
        String formattedDateTime = localDateTime.format(dateTimeFormatter);

        fundTransfer.setType(FundTransfer.TYPE_TRANSFER);
        fundTransfer.setAccountNumber("112233");
        fundTransfer.setDate(formattedDateTime);
        fundTransfer.setAmount(100);
        fundTransfer.setDestinationAccountNumber("223344");

        String expected = "transfer,112233," + formattedDateTime + ",100,223344";
        assertEquals(expected, fundTransfer.getRecord());
    }
}
