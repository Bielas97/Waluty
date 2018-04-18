package com.waluty.model.dto;

import com.waluty.model.Currency;

public class ConverterDto {
    public CurrencyDto fromCurrencyToCurrencyDto(Currency currency){
        return currency == null ? null :
                new CurrencyDto(currency.getId(), currency.getMid(), currency.getEffectiveDate(), currency.getCurrency(),
                        currency.getCode(), currency.getTable(), currency.getNo());
    }

    public Currency fromCurrencyDtoToCurrency(CurrencyDto currencyDto){
        return currencyDto == null ? null :
                new Currency(currencyDto.getId(), currencyDto.getMid(), currencyDto.getEffectiveDate(), currencyDto.getCurrency(),
                        currencyDto.getCode(), currencyDto.getTable(), currencyDto.getNo());
    }
}
