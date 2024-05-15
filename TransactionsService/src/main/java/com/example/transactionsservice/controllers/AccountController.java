package com.example.transactionsservice.controllers;

import com.example.transactionsservice.dto.AccountDto;
import com.example.transactionsservice.entities.Account;
import com.example.transactionsservice.model.Limit;
import com.example.transactionsservice.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor

public class AccountController {

    private final AccountService accountService;

    @PostMapping("/create")
    public AccountDto createNewAccount (@RequestBody Account acc) {

        return new AccountDto(accountService.createNewAccount(acc));
    }

    @PutMapping("/limits/set")
    public AccountDto setMonthLimit(@RequestBody Limit limit) {

        return new AccountDto(accountService.setMonthLimit(limit));
    }

}
