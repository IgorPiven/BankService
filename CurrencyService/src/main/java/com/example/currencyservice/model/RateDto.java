package com.example.currencyservice.model;

import com.example.currencyservice.entities.CurrentRate;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@JsonNaming(PropertyNamingStrategies.UpperSnakeCaseStrategy.class)
public class RateDto {

    private BigDecimal RUB;
    private BigDecimal KZT;

    public RateDto(CurrentRate rate) {
        this.RUB = rate.getRUB();
        this.KZT = rate.getKZT();
    }
}
