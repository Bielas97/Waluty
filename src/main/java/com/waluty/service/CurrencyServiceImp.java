package com.waluty.service;

import com.waluty.model.Currency;
import com.waluty.model.dto.ConverterDto;
import com.waluty.model.dto.CurrencyDto;
import com.waluty.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CurrencyServiceImp implements CurrencyService {
    private Map<String, List<Double>> comparedCurrencies = new LinkedHashMap<>();
    private ConverterDto converterDto;
    private CurrencyRepository currencyRepository;
    private Map<String, Double> differentMids = new LinkedHashMap<>();

    @Autowired
    public CurrencyServiceImp(ConverterDto converterDto, CurrencyRepository currencyRepository) {
        this.converterDto = converterDto;
        this.currencyRepository = currencyRepository;
    }

    private void setComparedCurrencies() {
        List<CurrencyDto> temp = new LinkedList<>();
        List<Currency> pom = currencyRepository.findAll();

        for (Currency x : pom)
            temp.add(converterDto.fromCurrencyToCurrencyDto(x));
    }

    public void setDifferentMids() {
        for (Map.Entry<String, List<Double>> entry : comparedCurrencies.entrySet()) {
            differentMids.put(entry.getKey(), entry.getValue().get(0) - entry.getValue().get(1));
        }
    }

    public Boolean compareCurrenciesByMids(Currency c1, Currency c2) {
        if (c1.getMid() > c2.getMid()) {
            return false;
        }
        return true;
    }

    @Override
    public List<CurrencyDto> getAllCurrency() {
        return currencyRepository.findAll()
                .stream()
                .map(converterDto::fromCurrencyToCurrencyDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CurrencyDto> getOneCurrency(Long id) {
        return currencyRepository.findById(id).map(p -> converterDto.fromCurrencyToCurrencyDto(p));
    }

    @Override
    public CurrencyDto addCurrency(CurrencyDto currencyDto) {
        return converterDto
                .fromCurrencyToCurrencyDto(
                        currencyRepository
                                .save(converterDto.fromCurrencyDtoToCurrency(currencyDto))
                );
    }
}
