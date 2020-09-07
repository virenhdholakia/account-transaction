package com.coding.challenge.transactions.enums;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TransactionTypeTest {

    @Test
    public void debitTransactionTypeEnumTest() {
        TransactionType transactionType = TransactionType.DEBIT;
        assertEquals(transactionType, TransactionType.DEBIT);
    }

    @Test
    public void creditTransactionTypeEnumTest() {
        TransactionType transactionType = TransactionType.CREDIT;
        assertEquals(transactionType, TransactionType.CREDIT);
    }

    @Test
    public void crossTransactionTypeEnumTest() {
        TransactionType transactionType = TransactionType.CREDIT;
        assertFalse(transactionType == TransactionType.DEBIT);

        transactionType = TransactionType.DEBIT;
        assertFalse(transactionType == TransactionType.CREDIT);
    }
}
