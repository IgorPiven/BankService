package com.example.transactionsservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum CategoryName {

    SERVICE (1),
    PRODUCT (2);

    private final int ctgValue;

}
