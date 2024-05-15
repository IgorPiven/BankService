package com.example.transactionsservice.controllers;

import com.example.transactionsservice.dto.ClientDto;
import com.example.transactionsservice.dto.ExceededDto;
import com.example.transactionsservice.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor

public class ClientController {

    private final ClientService clientService;

    @GetMapping("/get")
    public List<ClientDto> getAllClients() {

        return clientService.findAll()
                .stream()
                .map(ClientDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}/exceeded")
    public List<ExceededDto> getLimitsExceeded(@PathVariable Long id) {
        return clientService.findAllByLimitExceeded(id)
                .stream()
                .map(ExceededDto::new)
                .collect(Collectors.toList());
    }
}
