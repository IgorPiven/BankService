package com.example.transactionsservice.dto;

import com.example.transactionsservice.entities.Transaction;
import com.example.transactionsservice.enums.CurrencyName;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Setter
public class ExceededDto {

    private String accountFrom;
    private String accountTo;
    private BigDecimal txnSum;
    private CurrencyName currName;
    private BigDecimal monthLimit;
    private ZonedDateTime limitDate;
    private BigDecimal limitLeft;
    private boolean exceededLimit;

    public ExceededDto(Transaction txn) {
        this.accountFrom = txn.getAccFrom();
        this.accountTo = txn.getAccTo();
        this.txnSum = txn.getTxnSum();
        this.currName = txn.getCurrShrtName();
        this.monthLimit = txn.getMonthLimit();
        this.limitLeft = txn.getLimitLeft();
        this.exceededLimit = txn.isLimitExcd();

    }
}
