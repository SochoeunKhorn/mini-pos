package com.sochoeun.mapper;

import com.sochoeun.dto.ExchangeRateDto;
import com.sochoeun.model.ExchangeRate;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRateMapper {
    public ExchangeRate toExchangeRate(ExchangeRateDto dto) {
        return ExchangeRate.builder()
                .id(dto.getId())
                .exchangeRate(dto.getExchangeRate())
                .build();
    }

    public ExchangeRateDto toExchangeRateDto(ExchangeRate exchangeRate) {
        return ExchangeRateDto.builder()
                .id(exchangeRate.getId())
                .exchangeRate(exchangeRate.getExchangeRate())
                .build();
    }
}
