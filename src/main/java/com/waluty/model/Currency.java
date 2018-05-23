package com.waluty.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Currency {
    @Id
    @GeneratedValue
    private Long id;
    private Double mid; // średni kurs waluty
    private String effectiveDate; //data publikacji
    private String currency;
    private String code;
    private String tableId; //Interesują nas tabele A i B

}