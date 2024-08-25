package com.sochoeun.service;

import com.sochoeun.model.ExchangeRate;

import java.util.List;

public interface ExchangeRateService {

    ExchangeRate createExchangeRate(ExchangeRate ExchangeRate);
    List<ExchangeRate> getAllExchangeRates();
    ExchangeRate getExchangeRate(Long ExchangeRateId);
    ExchangeRate updateExchangeRate(ExchangeRate ExchangeRate);
    void deleteExchangeRate(Long ExchangeRateId);
}
