package com.coding.challenge.transactions.enums;

public enum AccountTypes {

    SAVINGS ("Savings"),
    CURRENT("Current");

    private String accountType;

    public String getAccountType() {
        return this.accountType;
    }

    AccountTypes(String accountType) {
        this.accountType = accountType;
    }

}
