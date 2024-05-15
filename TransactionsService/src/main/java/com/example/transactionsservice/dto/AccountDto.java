package com.example.transactionsservice.dto;

import com.example.transactionsservice.entities.Account;
import com.example.transactionsservice.enums.CategoryName;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;


@Getter
public class AccountDto {
    private String accNo;
    private CategoryName categoryName;
    private BigDecimal balance;
    private BigDecimal limitSet;
    private ZonedDateTime limitDate;
    private BigDecimal limitBalance;

    public AccountDto(Account account) {
        this.accNo = account.getAccNo();
        this.categoryName = account.getCategory().getCtgyName();
        this.balance = account.getBalance();
        this.limitSet = account.getLimitSet();
        this.limitDate = account.getLimitDate();
        this.limitBalance = account.getLimitBalance();
    }
}
