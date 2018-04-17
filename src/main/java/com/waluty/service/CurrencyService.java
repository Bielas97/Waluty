package com.waluty.service;

import com.waluty.model.dto.CurrencyDto;

import java.util.List;
import java.util.Optional;

public interface CurrencyService {
    List<CurrencyDto> getAllProducts();
    Optional<CurrencyDto> getOneProduct(Long id);
    CurrencyDto addProducer(CurrencyDto currencyDto);

    List<CurrencyDto> getAllProducers();
    Optional<CurrencyDto> getOneProducer(Long id);
}
