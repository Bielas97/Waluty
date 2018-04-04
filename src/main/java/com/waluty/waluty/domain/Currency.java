package com.waluty.waluty.domain;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Currency {
    @GeneratedValue


    private Double mid; // średni kurs waluty
    private String effectiveDate; //data publikacji
    private String currency;
    private String code;
    private String table; //Interesują nas tabele A i B
    private Double no; //numer tabeli

}