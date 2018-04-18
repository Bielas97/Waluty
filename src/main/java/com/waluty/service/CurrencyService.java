package com.waluty.service;

import com.waluty.model.Currency;
import com.waluty.model.dto.CurrencyDto;

import java.util.List;
import java.util.Optional;

public interface CurrencyService {
    List<Currency> getAllCurrency();
    Optional<Currency> getOneCurrency(Long id);

    CurrencyDto addCurrencyDto(CurrencyDto currencyDto);

    List<CurrencyDto> getAllCurrencyDto();
    Optional<CurrencyDto> getOneCurrencyDto(Long id);
}
