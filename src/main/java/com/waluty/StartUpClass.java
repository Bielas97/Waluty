package com.waluty;

import com.waluty.model.Currency;
import com.waluty.parser.Table;
import com.waluty.service.CurrencyService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class StartUpClass {
    private CurrencyService currencyService;

    public StartUpClass(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @PostConstruct
    public void setTable() {
        try {
            Table table = new Table();
            for (Currency c : table.getCurrencyList()) {
                currencyService.addOrUpdateCurrency(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
