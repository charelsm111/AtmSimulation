//package com.app;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ActiveAccountTest {
//
//    private ActiveAccount activeAccount;
//
//    @BeforeEach
//    void init() {
//        this.activeAccount = new ActiveAccount();
//        this.activeAccount.accounts = new ArrayList<>();
//
//        Map<String, Account> accountMap = new HashMap<>();
//
//        Account account1 = new Account();
//        account1.setName("John Doe");
//        account1.setPin("012108");
//        account1.setBalance(100);
//        account1.setAccountNumber("112233");
//        accountMap.put(account1.getAccountNumber(), account1);
//
//        Account account2 = new Account();
//        account2.setName("Jane Doe");
//        account2.setPin("932012");
//        account2.setBalance(30);
//        account2.setAccountNumber("112244");
//        accountMap.put(account2.getAccountNumber(), account2);
//
//        this.activeAccount.setAccounts(accountMap);
//    }
//
//    @Test
//    void testCheckAccount() {
//        String accountNumber = "112233";
//        String pin = "012108";
//
//        Account account = this.activeAccount.getAccount(accountNumber, pin);
//        assertEquals("John Doe", account.getName());
//
//        accountNumber = "112233";
//        pin = "111111";
//
//        account = this.activeAccount.getAccount(accountNumber, pin);
//        assertNull(account);
//    }
//
//    @Test
//    void testValidateAccountExistence() {
//        String accountNumber = "112233";
//        String pin = "012108";
//
//        assertFalse(this.activeAccount.validateAccountExistence(accountNumber, pin).getIsError());
//        assertNull(this.activeAccount.validateAccountExistence(accountNumber, pin).getMessage());
//
//        accountNumber = "112233";
//        pin = "111111";
//
//        assertTrue(this.activeAccount.validateAccountExistence(accountNumber, pin).getIsError());
//        String expected = "Invalid Account Number/PIN if records is not exist";
//        assertEquals(expected, this.activeAccount.validateAccountExistence(accountNumber, pin).getMessage());
//    }
//
//    @Test
//    void testValidateAccountNumberIsNumeric() {
//        String accountNumber = "112233";
//        assertFalse(this.activeAccount.validateAccountNumberIsNumeric(accountNumber).getIsError());
//        assertNull(this.activeAccount.validateAccountNumberIsNumeric(accountNumber).getMessage());
//
//        accountNumber = "1122ab";
//        assertTrue(this.activeAccount.validateAccountNumberIsNumeric(accountNumber).getIsError());
//        String expected = "Invalid account";
//        assertEquals(expected, this.activeAccount.validateAccountNumberIsNumeric(accountNumber).getMessage());
//    }
//
//    @Test
//    void testValidateDestinationAccountNumber() {
//        String accountNumber = "112233";
//        assertFalse(this.activeAccount.validateDestinationAccountNumber(accountNumber).getIsError());
//        assertNull(this.activeAccount.validateDestinationAccountNumber(accountNumber).getMessage());
//
//        accountNumber = "221133";
//        assertTrue(this.activeAccount.validateDestinationAccountNumber(accountNumber).getIsError());
//        String expected = "Invalid account";
//        assertEquals(expected, this.activeAccount.validateDestinationAccountNumber(accountNumber).getMessage());
//    }
//
//    @Test
//    void testGetAccountByAccountNumber() {
//        String accountNumber = "112233";
//        Account account = this.activeAccount.getAccount(accountNumber);
//        assertEquals("John Doe", account.getName());
//
//        accountNumber = "221233";
//        account = this.activeAccount.getAccount(accountNumber);
//        assertNull(account);
//    }
//
//
//}