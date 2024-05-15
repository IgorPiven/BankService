package com.example.transactionsservice.entities;

import com.example.transactionsservice.enums.CategoryName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "exps_ctgries")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ctgy_id")
    @JsonIgnore
    private int ctgyId;

    @Column(name = "ctgy_name")
    @Enumerated(EnumType.STRING)
    private CategoryName ctgyName;

    @OneToOne(mappedBy = "category")
    @JsonIgnore
    private Account account;
}
