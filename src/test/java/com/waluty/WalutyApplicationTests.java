package com.waluty;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import org.springframework.ui.Model;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WalutyApplicationTests {
	ConverterDto convDto = new ConverterDto();
	CurrencyController control = new CurrencyController();
	Model model = new Model();

	currDto = convDto.fromCurrencyToCurrencyDto(null);
	curr = convDto.fromCurrencyDtoToCurrency(null);

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
		testMessage = "getAll";
		assertEquals(testMessage,control.getCurrency(model));
	}

}
