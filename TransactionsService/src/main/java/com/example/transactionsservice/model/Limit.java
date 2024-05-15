package com.example.transactionsservice.model;

import com.example.transactionsservice.enums.CategoryName;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Limit {

    private String accNo;
    private CategoryName ctgyName;
    private BigDecimal limitSum;
}
