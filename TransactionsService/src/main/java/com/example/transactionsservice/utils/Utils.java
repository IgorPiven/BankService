package com.example.transactionsservice.utils;


import com.example.transactionsservice.enums.CurrencyName;
import com.example.transactionsservice.exceptions.IllegalOperationException;
import com.example.transactionsservice.services.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class Utils {

    private final CurrencyService currencyService;

    public BigDecimal convertToUSD(BigDecimal sum, CurrencyName currencyName) {

        return sum.divide(currencyService.getCurrencyValue().get(currencyName), RoundingMode.DOWN);
    }

    public BigDecimal checkOperationValue(BigDecimal value) {

        if (value.signum() < 0) throw
                new IllegalOperationException("Operation failed. Given value is below zero");

        else return value;
    }

    public void checkDate(ZonedDateTime date) {

        LocalDate currentDate = ZonedDateTime.now().toLocalDate();

        if (date.toLocalDate().isAfter(currentDate) || date.toLocalDate().isBefore(currentDate))
            throw new IllegalOperationException("Transaction failed. Date is either expired, or not yet");
    }

    public BigDecimal setLimitByValue (BigDecimal newLimit, BigDecimal currLimitBalance) {

        if (newLimit.compareTo(currLimitBalance) < 0) return newLimit;
        else return currLimitBalance;
    }
}
