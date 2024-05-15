package com.example.transactionsservice.services;

import com.example.transactionsservice.enums.CurrencyName;
import com.example.transactionsservice.exceptions.ResourceNotAvailableException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

@Service
@SuppressWarnings("unchecked")
@Slf4j
public class CurrencyService {

    @Value("${currencies.url}") private String currencyURL;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();
    private Map<String, Double> currenciesMap = new HashMap<>();

    @Scheduled(cron = "${cron.expression}", zone = "Europe/Moscow")
    public void getActualCurrencies() {

        try {
            String json = restTemplate.getForObject(new URI(currencyURL), String.class);
            currenciesMap = mapper.readValue(json, HashMap.class);

        } catch (RestClientException | IOException | URISyntaxException e) {
            throw new ResourceNotAvailableException("Service may not be available");
        }
    }

    public EnumMap<CurrencyName, BigDecimal> getCurrencyValue() {

        if (currenciesMap.isEmpty()) {
            log.error("Data is empty. Recalling currency service");
            getActualCurrencies();
        }

        EnumMap<CurrencyName, BigDecimal> currenciesEnumMap = new EnumMap<>(CurrencyName.class);

        currenciesEnumMap.put(CurrencyName.RUB,
                BigDecimal.valueOf(currenciesMap.get(CurrencyName.RUB.toString())));
        currenciesEnumMap.put(CurrencyName.KZT,
                BigDecimal.valueOf(currenciesMap.get(CurrencyName.KZT.toString())));

        return currenciesEnumMap;
    }

}

