package com.example.currencyservice.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.example.currencyservice.model.CurrencyType.*;


public class Rates {

    private Map<String, BigDecimal> rates;

    public Map<String, BigDecimal> getRates() {

        Map<String, BigDecimal> currentRates = new HashMap<>();

        currentRates.put(RUB.toString(), rates.get(RUB.toString()));
        currentRates.put(KZT.toString(), rates.get(KZT.toString()));

        return currentRates;
    }

}
