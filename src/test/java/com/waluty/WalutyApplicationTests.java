package com.waluty;

import com.waluty.controllers.CurrencyController;
import com.waluty.model.Currency;
import com.waluty.model.dto.ConverterDto;
import com.waluty.model.dto.CurrencyDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WalutyApplicationTests {
    ConverterDto convDto = new ConverterDto();
    CurrencyController control = new CurrencyController();
    Model model = new ConcurrentModel();

    CurrencyDto currDto = convDto.fromCurrencyToCurrencyDto(null);
    Currency curr = convDto.fromCurrencyDtoToCurrency(null);

    @Test
    public void testFromCurrencyToCurrencyDto() {
        assertNull(currDto);
    }

    @Test
    public void testFromCurrencyDtoToCurrency() {
        assertNull(curr);
    }

    @Test
    public void testGetCurrency() {
        String testMessage = "currency";
        assertEquals(testMessage, control.getCurrency(model));
    }

    @Test
    public void testCurrencyInsert() {
        String testMessage = "currencyInsert";
        assertEquals(testMessage, control.getCurrency(model));
    }

    @Test
    public void testCurrencyInsertPost() {
        String testMessage = "redirect:/currency/selectAll";
        assertEquals(testMessage, control.getCurrency(model));
    }

    @Test
    public void testCurrencyUpdate() {
        String testMessage = "update";
        assertEquals(testMessage, control.getCurrency(model));
    }

    @Test
    public void testCurrencyUpdatePost() {
        String testMessage = "redirect:/currency/selectAll";
        assertEquals(testMessage, control.getCurrency(model));
    }

    @Test
    public void testPersonRemovetGet() {
        String testMessage = "redirect:/currency/selectAll";
        assertEquals(testMessage, control.getCurrency(model));
    }
    @Test
    public void testSetUPCurrency() {
        Currency currencyOne = new Currency();
        Currency currencyTwo = new Currency(Long id, Double mid, String effectiveDate, String currency, String code,String tableId);
        assertEquals(true, currencyOne.equals(currencyTwo));
    }
}