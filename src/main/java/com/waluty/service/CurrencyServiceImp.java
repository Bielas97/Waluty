package com.waluty.service;

import com.waluty.model.Currency;
import com.waluty.model.dto.ConverterDto;
import com.waluty.model.dto.CurrencyDto;
import com.waluty.repository.CurrencyRepositoryImp;
import lombok.AllArgsConstructor;

import java.util.stream.Collectors;

import java.util.*;

@AllArgsConstructor
public class CurrencyServiceImp implements CurrencyService {
    private Map<String, List<Double>> comparedCurrencies = new LinkedHashMap<>();
    private CurrencyDto currencyDto;
    private ConverterDto converterDto;
    private CurrencyRepository currencyRepository;
    private Map<String, Double> differentMids = new LinkedHashMap<>();

    private void setComparedCurrencies() {
        List<Currency> temp = currencyDto.getAll();

        temp.stream().forEach(c -> {
            if (!comparedCurrencies.containsKey(c.getCurrency())) {
                List<Double> mids = new ArrayList<>();
                mids.add(c.getMid());
                comparedCurrencies.put(c.getCurrency(), mids);
            } else {
                comparedCurrencies.get(c.getCurrency()).add(c.getMid());
            }
        });
    }

    public void setDifferentMids() {
        for (Map.Entry<String, List<Double>> entry : comparedCurrencies.entrySet()) {
            differentMids.put(entry.getKey(), entry.getValue().get(0) - entry.getValue().get(1));
        }
    }

    public List<Currency> getThreeBestCurrencies() {
        List<Currency> best = new ArrayList<>();
        setComparedCurrencies();
        setDifferentMids();
        //sortowanie mapy po wartosci:
        differentMids = differentMids.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        int counter = 0;
        for (Map.Entry entry : differentMids.entrySet()) {
            Optional<Currency> currency = currencyDto.getCurrencyByCurrency(entry.getKey().toString());
            best.add(currency.get());
            counter++;
            if (counter == 2) break;
        }

        return best;
    }

    public Boolean compareCurrenciesByMids(Currency c1, Currency c2) {
        if (c1.getMid() > c2.getMid()) {
            return false;
        }
        return true;
    }

    @Override
    List<Currency> getAllCurrency() {
        return currencyRepository.findAll()
                .stream()
                .map(converterDto::fromCurrencyDtoToCurrency)
                .collect(Collectors.toList());
    }

    @Override
    Optional<Currency> getOneCurrency(Long id) {
        return currencyRepository.findById(id).map(p -> converterDto.fromCurrencyDtoToCurrency(p));
    }

    @Override
    CurrencyDto addCurrencyDto(CurrencyDto currencyDto) {
        return currencyRepository.save(currencyDto);
    }

    @Override
    List<CurrencyDto> getAllCurrencyDto() {
        return currencyRepository.findAll().collect(Collectors.toList());
    }

    @Override
    Optional<CurrencyDto> getOneCurrencyDto(Long id) {
        return currencyRepository.findById(id);
    }
}
