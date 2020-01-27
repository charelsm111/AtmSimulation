//package com.app;
//
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class WithdrawTest {
//
//    @Test
//    void testGetRecord() {
//        Withdraw withdraw = new Withdraw();
//
//        LocalDateTime localDateTime = LocalDateTime.now();
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm a");
//        String formattedDateTime = localDateTime.format(dateTimeFormatter);
//
//        withdraw.setType(Withdraw.TYPE_WITHDRAW);
//        withdraw.setAccountNumber("112233");
//        withdraw.setDate(formattedDateTime);
//        withdraw.setAmount(100);
//
//        String expected = "withdraw,112233," + formattedDateTime + ",100";
//        assertEquals(expected, withdraw.getRecord());
//    }
//}
