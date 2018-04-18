package com.waluty.service;

import com.waluty.model.dto.CurrencyDto;

import java.util.List;
import java.util.Optional;

public interface CurrencyService {
    List<CurrencyDto> getAllCurrency();
    Optional<CurrencyDto> getOneCurrency(Long id);

    CurrencyDto addCurrency(CurrencyDto currencyDto);
}
