package com.waluty.service;

import com.waluty.model.Currency;
import com.waluty.model.dto.ConverterDto;
import com.waluty.model.dto.CurrencyDto;
import com.waluty.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import java.util.stream.Collectors;

public class CurrencyServiceImp implements CurrencyService {
    private Map<String, List<Double>> comparedCurrencies = new LinkedHashMap<>();
    private CurrencyDto currencyDto;
    private ConverterDto converterDto;
    private CurrencyRepository currencyRepository;
    private Map<String, Double> differentMids = new LinkedHashMap<>();

    @Autowired
    public CurrencyServiceImp(Map<String, List<Double>> comparedCurrencies, CurrencyDto currencyDto, ConverterDto converterDto, CurrencyRepository currencyRepository, Map<String, Double> differentMids) {
        this.comparedCurrencies = comparedCurrencies;
        this.currencyDto = currencyDto;
        this.converterDto = converterDto;
        this.currencyRepository = currencyRepository;
        this.differentMids = differentMids;
    }

    private void setComparedCurrencies() {
        List<CurrencyDto> temp = new LinkedList<>();
        List<Currency> pom = currencyRepository.getAll();

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
    public List<CurrencyDto> getAllProducts() {
        return currencyRepository.findAll()
                .stream()
                .map(converterDto::fromCurrencyToCurrencyDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CurrencyDto> getOneProducts(Long id) {
        return currencyRepository.findById(id).map(p -> converterDto.fromCurrencyToCurrencyDto(p));
    }

    @Override
    public CurrencyDto addCurrency(CurrencyDto currencyDto) {
        return currencyRepository.save(converterDto.fromCurrencyDtoToCurrency(currencyDto));
    }
}
