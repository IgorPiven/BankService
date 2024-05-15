package com.example.transactionsservice.controllers;

import com.example.transactionsservice.entities.Transaction;
import com.example.transactionsservice.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor

public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/in")
    public ResponseEntity<?> getIncomingTransaction(
            @RequestBody Transaction txn,
            UriComponentsBuilder uriComponentsBuilder) {

        Transaction transaction = transactionService.createNewTransaction(txn);

        return ResponseEntity
                .created(uriComponentsBuilder
                        .path("/{id}")
                        .build(Map.of("id", transaction.getTxnId())))
                .body(transaction);
    }
}

