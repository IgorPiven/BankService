package com.example.transactionsservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "accounts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acc_id")
    @JsonIgnore
    private Long accId;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "acc_no")
    private String accNo;

    @OneToOne
    @JoinColumn(name = "exps_ctgy_id")
    private Category category;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "limit_set")
    private BigDecimal limitSet;

    @Column(name = "limit_date")
    private ZonedDateTime limitDate;

    @Column(name = "limit_balance")
    private BigDecimal limitBalance;
}


