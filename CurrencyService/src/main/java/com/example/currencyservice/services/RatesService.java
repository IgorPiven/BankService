package com.example.currencyservice.services;

import com.example.currencyservice.entities.CurrentRate;
import com.example.currencyservice.model.Rates;
import com.example.currencyservice.repositories.RateRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZonedDateTime;

import static com.example.currencyservice.model.CurrencyType.*;


@Service
@RequiredArgsConstructor
public class RatesService {

    private final RateApiService rateApiService;
    private final RateRepository rateRepository;

    @Value("${exchange.app_id}")
    String app_id;

    @Cacheable("currencies")
    public CurrentRate getRates() {

        CurrentRate currentRate = rateRepository.findLastRate();

        if (currentRate.getRateDate().isBefore(ZonedDateTime.now().toLocalDate())) {
            saveRatesToDataBase();
        }

        return rateRepository.findLastRate();
    }

    @Scheduled(cron = "${cron.expression}", zone = "Europe/Moscow")
    @CacheEvict("currencies")
    public void saveRatesToDataBase() {

        CurrentRate currentRate = new CurrentRate();

        Rates rates = rateApiService.getCurrentRates(app_id);

        currentRate.setRUB(rates.getRates().get(RUB.toString()).setScale(2, RoundingMode.DOWN));
        currentRate.setKZT(rates.getRates().get(KZT.toString()).setScale(2, RoundingMode.DOWN));

        currentRate.setRateDate(LocalDate.now());

        rateRepository.save(currentRate);
    }
}
