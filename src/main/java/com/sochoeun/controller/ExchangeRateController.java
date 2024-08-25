package com.sochoeun.controller;

import com.sochoeun.dto.ExchangeRateDto;
import com.sochoeun.mapper.ExchangeRateMapper;
import com.sochoeun.model.ExchangeRate;
import com.sochoeun.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/exchange-rate")
public class ExchangeRateController {
    private final ExchangeRateService exchangeRateService;
    private final ExchangeRateMapper exchangeRateMapper;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ExchangeRateDto dto) {
        ExchangeRate exchangeRate = exchangeRateService.createExchangeRate(exchangeRateMapper.toExchangeRate(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(exchangeRateMapper.toExchangeRateDto(exchangeRate));
    }

    @GetMapping
    public ResponseEntity<?> getExchangeRates() {
        List<ExchangeRate> allExchangeRates = exchangeRateService.getAllExchangeRates();
        List<ExchangeRateDto> list = allExchangeRates.stream()
                .map(exchangeRateMapper::toExchangeRateDto)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{rate-id}")
    public ResponseEntity<?> getExchangeRates(@PathVariable("rate-id") long id) {
        ExchangeRate exchangeRate = exchangeRateService.getExchangeRate(id);
        return ResponseEntity.status(HttpStatus.OK).body(exchangeRateMapper.toExchangeRateDto(exchangeRate));
    }

    @DeleteMapping("/{rate-id}")
    public ResponseEntity<?> deleteExchangeRates(@PathVariable("rate-id") long id) {
        exchangeRateService.deleteExchangeRate(id);
        return ResponseEntity.status(HttpStatus.OK).body("Exchange rate deleted");
    }

    @PutMapping()
    public ResponseEntity<?> updateExchangeRates(@RequestBody ExchangeRateDto dto) {
        ExchangeRate exchangeRate = exchangeRateService.updateExchangeRate(exchangeRateMapper.toExchangeRate(dto));
        return ResponseEntity.status(HttpStatus.OK).body(exchangeRateMapper.toExchangeRateDto(exchangeRate));
    }
}
