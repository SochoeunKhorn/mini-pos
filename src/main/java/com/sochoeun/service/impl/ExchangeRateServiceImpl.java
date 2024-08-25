package com.sochoeun.service.impl;

import com.sochoeun.handler.NotFoundException;
import com.sochoeun.model.ExchangeRate;
import com.sochoeun.repository.ExchangeRateRepository;
import com.sochoeun.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {
    private final ExchangeRateRepository exchangeRateRepository;
    @Override
    public ExchangeRate createExchangeRate(ExchangeRate request) {
        return exchangeRateRepository.save(request);
    }

    @Override
    public List<ExchangeRate> getAllExchangeRates() {
        return exchangeRateRepository.findAllByDeletedFalse();
    }

    @Override
    public ExchangeRate getExchangeRate(Long id) {
        return exchangeRateRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(()-> new NotFoundException("ExchangeRate not found with id: " + id));
    }

    @Override
    public ExchangeRate updateExchangeRate(ExchangeRate request) {
        ExchangeRate exchangeRate = getExchangeRate(request.getId());
        exchangeRate.setExchangeRate(request.getExchangeRate());
        return exchangeRateRepository.save(exchangeRate);
    }

    @Override
    public void deleteExchangeRate(Long id) {
        ExchangeRate exchangeRate = getExchangeRate(id);
        exchangeRate.setDeleted(true);
        exchangeRateRepository.save(exchangeRate);
    }
}
