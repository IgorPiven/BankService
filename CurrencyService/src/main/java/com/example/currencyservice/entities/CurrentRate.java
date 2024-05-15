package com.example.currencyservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "rates")
@NoArgsConstructor
@Getter
@Setter


public class CurrentRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rate_id")
    private Long rateId;

    @Column(name = "rate_rub")
    private BigDecimal RUB;

    @Column(name = "rate_kzt")
    private BigDecimal KZT;

    @Column(name = "rate_date")
    private LocalDate rateDate;
}
