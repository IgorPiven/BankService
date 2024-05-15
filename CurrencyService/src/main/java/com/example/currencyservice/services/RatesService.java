package com.example.currencyservice.services;

import com.example.currencyservice.entities.CurrentRate;
import com.example.currencyservice.model.Rates;
import com.example.currencyservice.repositories.RateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.time.LocalDate;

import static com.example.currencyservice.model.CurrencyType.*;


@Service
@RequiredArgsConstructor
public class RatesService {

    private final RateApiService rateApiService;
    private final RateRepository rateRepository;

    @Value("${exchange.app_id}")
    String app_id;

    public CurrentRate getRates() {
        return rateRepository.findLastRate();
    }

    @Scheduled(cron = "${cron.expression}", zone = "Europe/Moscow")
    public void saveRatesToDataBase() {

        CurrentRate currentRate = new CurrentRate();

        Rates rates = rateApiService.getCurrentRates(app_id);

        currentRate.setRUB(rates.getRates().get(RUB.toString()).setScale(2, RoundingMode.DOWN));
        currentRate.setKZT(rates.getRates().get(KZT.toString()).setScale(2, RoundingMode.DOWN));

        currentRate.setRateDate(LocalDate.now());

        rateRepository.save(currentRate);
    }
}
