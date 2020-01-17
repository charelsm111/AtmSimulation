package com.app;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

// TODO: Add more test
public class TransactionTest {

    @Test
    void testTransactionFileExist() {
        File file = new File(ActiveAccount.PATHNAME);

        /*
        * This should be fails at the first run
        * because there'no transactions
         */
        assertTrue(file.exists());
    }
}
