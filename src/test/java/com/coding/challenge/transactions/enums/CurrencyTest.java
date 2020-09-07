package com.coding.challenge.transactions.enums;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CurrencyTest {

    @Test
    public void simpleCurrencyTest() {
        Currency currency = Currency.AUD;
        String currencyAUD = "AUD";
        assertEquals(currency, Currency.AUD);
        assertEquals(currencyAUD, Currency.AUD.toString());

        currency = Currency.SGD;
        String currencySGD = "SGD";
        assertEquals(currency, Currency.SGD);
        assertEquals(currencySGD, Currency.SGD.toString());

        currency = Currency.USD;
        String currencyUSD = "USD";
        assertEquals(currency, Currency.USD);
        assertEquals(currencyUSD, Currency.USD.toString());
    }
}
