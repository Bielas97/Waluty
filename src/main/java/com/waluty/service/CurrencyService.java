package com.waluty.service;

import com.waluty.model.Currency;
import com.waluty.model.dto.CurrencyDto;

import java.util.List;
import java.util.Optional;

public interface CurrencyService {

    List<CurrencyDto> getAllProducts();
    Optional<CurrencyDto> getOneCurrency(Long id);

    CurrencyDto removeCurrency(Long id);

    CurrencyDto addOrUpdateCurrency(CurrencyDto currencyDto);
    Currency addOrUpdateCurrency(Currency currency);
}
