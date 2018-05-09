package com.waluty.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrencyDto {
    private Long id;
    private Double mid; // średni kurs waluty
    private String effectiveDate; //data publikacji
    private String currency;
    private String code;
    private String tableId; //Interesują nas tabele A i B
}
