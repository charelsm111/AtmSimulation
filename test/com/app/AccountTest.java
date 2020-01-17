package com.app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AccountTest {

    private Account account = new Account();

    @BeforeEach
    void init() {
        this.account.setName("Charel Samuel");
        this.account.setAccountNumber("112233");
        this.account.setPin("123456");
        this.account.setBalance(100);
    }

    @Test
    void testGetName() {
        assertEquals("Charel Samuel", this.account.getName());
    }

    @Test
    void testGetAccountNumber() {
        assertEquals("112233", this.account.getAccountNumber());
    }

    @Test
    void testGetPin() {
        assertEquals("123456", this.account.getPin());
    }

    @Test
    void testDecreaseBalance() {
        this.account.setWithdrawal(20);
        this.account.decreaseBalance();
        assertEquals(80, this.account.getBalance());
    }

    @Test
    void testValidateRemainingBalance() {
        this.account.setWithdrawal(120);
        assertTrue(this.account.validateRemainingBalance().getIsError());
        assertEquals("Insufficient Balance $120", this.account.validateRemainingBalance().getMessage());

        this.account.setWithdrawal(80);
        assertFalse(this.account.validateRemainingBalance().getIsError());
        assertNull(this.account.validateRemainingBalance().getMessage());
    }

    @Test
    void testValidateMaximumDraw() {
        this.account.setWithdrawal(1500);
        assertTrue(this.account.validateMaximumWithdraw().getIsError());
        assertEquals("Maximum amount to withdraw is $1000", this.account.validateMaximumWithdraw().getMessage());
    }

    @Test
    void testValidateWithdrawAmountIsTenMultiply() {
        this.account.setWithdrawal(23);
        assertTrue(this.account.validateWithdrawAmountIsTenMultiply().getIsError());
        assertEquals("Invalid amount", this.account.validateWithdrawAmountIsTenMultiply().getMessage());
    }

    @Test
    void testValidateMaximumTransfer() {
        this.account.setTransferAmount(1100);
        assertTrue(this.account.validateMaximumTransfer().getIsError());
        String expected = "Maximum amount to withdraw is $1000";
        assertEquals(expected, this.account.validateMaximumTransfer().getMessage());

        this.account.setTransferAmount(980);
        assertFalse(this.account.validateMaximumTransfer().getIsError());
        assertNull(this.account.validateMaximumTransfer().getMessage());
    }

    @Test
    void testValidateMinimumTransfer() {
        this.account.setTransferAmount(0);
        assertTrue(this.account.validateMinimumTransfer().getIsError());
        assertEquals("Minimum amount to withdraw is $1", this.account.validateMinimumTransfer().getMessage());

        this.account.setTransferAmount(1);
        assertFalse(this.account.validateMinimumTransfer().getIsError());
        assertNull(this.account.validateMinimumTransfer().getMessage());
    }

    @Test
    void testTransferFund() {
        this.account.setTransferAmount(20);

        Account destinationAccount = new Account();
        destinationAccount.setBalance(100);

        this.account.transferFund(destinationAccount);
        assertEquals(120, destinationAccount.getBalance());
        assertEquals(80, this.account.getBalance());
    }

    @Test
    void testValidateAccountNumberLength() {
        assertFalse(this.account.validateAccountNumberLength().getIsError());
        assertNull(this.account.validateAccountNumberLength().getMessage());

        this.account.setAccountNumber("12345");
        assertTrue(this.account.validateAccountNumberLength().getIsError());
        String expected = "Account Number should have 6 digits length";
        assertEquals(expected, this.account.validateAccountNumberLength().getMessage());
    }

    @Test
    void testValidatePinLength() {
        assertFalse(this.account.validatePinLength().getIsError());
        assertNull(this.account.validatePinLength().getMessage());

        this.account.setPin("12345");
        assertTrue(this.account.validatePinLength().getIsError());
        assertEquals("PIN should have 6 digits length", this.account.validatePinLength().getMessage());
    }

    @Test
    void testPinIsNumeric() {
        assertFalse(this.account.validatePinIsNumeric().getIsError());
        assertNull(this.account.validatePinIsNumeric().getMessage());

        this.account.setPin("12345a");
        assertTrue(this.account.validatePinIsNumeric().getIsError());
        assertEquals("PIN should only contains numbers", this.account.validatePinIsNumeric().getMessage());
    }

    @Test
    void testAccountNumberIsNumeric() {
        assertFalse(this.account.validateAccountNumberIsNumeric().getIsError());
        assertNull(this.account.validateAccountNumberIsNumeric().getMessage());

        this.account.setAccountNumber("12345a");
        assertTrue(this.account.validateAccountNumberIsNumeric().getIsError());
        String expected = "Account Number should only contains numbers";
        assertEquals(expected, this.account.validateAccountNumberIsNumeric().getMessage());
    }
}