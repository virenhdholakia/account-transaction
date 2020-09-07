package com.coding.challenge.transactions.enums;

public enum TransactionType {

    DEBIT ("DEBIT"),
    CREDIT ("CREDIT");

    private String transactionType;

    public String getTransactionType() {
        return this.transactionType;
    }

    TransactionType (String transactionType) {
        this.transactionType = transactionType;
    }
}
