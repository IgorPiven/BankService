package com.example.transactionsservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "client_info")
    private String clientInfo;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private Set<Account> accounts;

    @OneToMany(mappedBy = "txnClientId")
    private List<Transaction> transactions;

}
