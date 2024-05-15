package com.example.currencyservice.controllers;


import com.example.currencyservice.model.RateDto;
import com.example.currencyservice.services.RatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class RateController {

    public final RatesService ratesService;

    @GetMapping("/rates")
    public RateDto getCurrentRates() {

        return new RateDto(ratesService.getRates());
    }
}
