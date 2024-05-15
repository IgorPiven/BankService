package com.example.transactionsservice.dto;

import com.example.transactionsservice.entities.Account;
import com.example.transactionsservice.entities.Client;
import lombok.Getter;

import java.util.Set;

@Getter
public class ClientDto {

    private String name;
    private Set<Account> accounts;

    public ClientDto(Client client) {
        this.name = client.getClientInfo();
        this.accounts = client.getAccounts();
    }
}
