package com.example.transactionsservice.entities;

import com.example.transactionsservice.enums.CategoryName;
import com.example.transactionsservice.enums.CurrencyName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "txns")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "txn_id")
    private Long txnId;

    @ManyToOne
    @JoinColumn(name = "txn_client_id")
    @JsonIgnore
    private Client txnClientId;

    @Column(name = "acc_from")
    private String accFrom;

    @Column(name = "acc_to")
    private String accTo;

    @Column(name = "curr_shrt_name")
    @Enumerated(EnumType.STRING)
    private CurrencyName currShrtName;

    @Column(name = " txn_sum")
    private BigDecimal txnSum;

    @Column(name = " txn_sum_usd")
    private BigDecimal txnSumUsd;

    @Column(name = "exps_ctgy")
    @Enumerated(EnumType.STRING)
    private CategoryName expsCtgy;

    @Column(name = "txn_date")
    private ZonedDateTime txnDate;

    @Column(name = "month_limit")
    private BigDecimal monthLimit;

    @Column(name = "limit_left")
    private BigDecimal limitLeft;

    @Column(name = "limit_excd")
    private boolean limitExcd;
}
