package com.example.transactionsservice.services;

import com.example.transactionsservice.entities.Client;
import com.example.transactionsservice.entities.Transaction;
import com.example.transactionsservice.exceptions.ResourceExistsButEmptyException;
import com.example.transactionsservice.exceptions.ResourceNotFoundException;
import com.example.transactionsservice.repositories.AccountRepository;
import com.example.transactionsservice.repositories.ClientRepository;
import com.example.transactionsservice.repositories.TransactionRepository;
import com.example.transactionsservice.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    public final TransactionRepository transactionRepository;
    public final AccountRepository accountRepository;
    public final Utils utils;

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(Long id) {
        return clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Client with ID: " + id + " not found"));
    }

    public List<Transaction> findAllByLimitExceeded(Long id) {

        clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Client with ID: " + id + " not found"));

        if (transactionRepository.findAllByLimitExceeded(id).isEmpty())
            throw new ResourceExistsButEmptyException("No transactions with exceeded limit found for client with ID: " + id);

        else return transactionRepository.findAllByLimitExceeded(id);
    }


}