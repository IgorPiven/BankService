package com.example.currencyservice.services;


import com.example.currencyservice.model.Rates;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "RateRequest", url = "${exchange.url}")
public interface RateApiService {

    @GetMapping
    Rates getCurrentRates(@RequestParam String app_id);

}
