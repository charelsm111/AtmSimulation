package com.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActiveAccountTest {

    private ActiveAccount activeAccount;

    @BeforeEach
    void init() {
        this.activeAccount = new ActiveAccount();
    }

    @Test
    void testCheckAccount() {
        String accountNumber = "112233";
        String pin = "012108";

        Account account = this.activeAccount.getAccount(accountNumber, pin);
        assertEquals("John Doe", account.getName());

        accountNumber = "112233";
        pin = "111111";

        account = this.activeAccount.getAccount(accountNumber, pin);
        assertNull(account);
    }

    @Test
    void testValidateAccountExistence() {
        String accountNumber = "112233";
        String pin = "012108";

        assertFalse(this.activeAccount.validateAccountExistence(accountNumber, pin).getIsError());
        assertNull(this.activeAccount.validateAccountExistence(accountNumber, pin).getMessage());

        accountNumber = "112233";
        pin = "111111";

        assertTrue(this.activeAccount.validateAccountExistence(accountNumber, pin).getIsError());
        String expected = "Invalid Account Number/PIN if records is not exist";
        assertEquals(expected, this.activeAccount.validateAccountExistence(accountNumber, pin).getMessage());
    }

    @Test
    void testValidateAccountNumberIsNumeric() {
        String accountNumber = "112233";
        assertFalse(this.activeAccount.validateAccountNumberIsNumeric(accountNumber).getIsError());
        assertNull(this.activeAccount.validateAccountNumberIsNumeric(accountNumber).getMessage());

        accountNumber = "1122ab";
        assertTrue(this.activeAccount.validateAccountNumberIsNumeric(accountNumber).getIsError());
        String expected = "Invalid account";
        assertEquals(expected, this.activeAccount.validateAccountNumberIsNumeric(accountNumber).getMessage());
    }

    @Test
    void testValidateDestinationAccountNumber() {
        String accountNumber = "112233";
        assertFalse(this.activeAccount.validateDestinationAccountNumber(accountNumber).getIsError());
        assertNull(this.activeAccount.validateDestinationAccountNumber(accountNumber).getMessage());

        accountNumber = "221133";
        assertTrue(this.activeAccount.validateDestinationAccountNumber(accountNumber).getIsError());
        String expected = "Invalid account";
        assertEquals(expected, this.activeAccount.validateDestinationAccountNumber(accountNumber).getMessage());
    }

    @Test
    void testGetAccountByAccountNumber() {
        String accountNumber = "112233";
        Account account = this.activeAccount.getAccount(accountNumber);
        assertEquals("John Doe", account.getName());

        accountNumber = "221233";
        account = this.activeAccount.getAccount(accountNumber);
        assertNull(account);
    }

}