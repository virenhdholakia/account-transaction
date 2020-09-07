package com.coding.challenge.transactions.enums;

public enum Currency {

    AUD("AUD"),
    SGD("SGD"),
    USD("USD");

    private String currency;

    public String getCurrency() {
        return this.currency;
    }

    Currency(String currency) {
        this.currency = currency;
    }
}
