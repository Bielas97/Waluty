package com.waluty.parser;


import com.waluty.model.Currency;
import com.waluty.model.dto.ConverterDto;
import com.waluty.repository.CurrencyRepository;
import com.waluty.service.CurrencyService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class Table {
    private static final String NBP = "http://api.nbp.pl/api/exchangerates/tables/A/";
    private List<Currency> currencyList = new ArrayList<>();

    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    private CurrencyService currencyService;
    private ConverterDto converterDto;
    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    public Table(CurrencyService currencyService, ConverterDto converterDto) {
        this.currencyService = currencyService;
        this.converterDto = converterDto;
    }

    private void setTable() throws IOException {

        java.net.URL url = new URL(NBP);
        URLConnection urlConnection = url.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String s = "";
        JSONParser jsonParser = new JSONParser();
        while ((s = bufferedReader.readLine()) != null) {
            try {
                JSONArray jsonArray = (JSONArray) jsonParser.parse(s);
                for (Object ob : jsonArray) {
                    JSONObject jObject = (JSONObject) ob;
                    String effectiveDate = (String) jObject.get("effectiveDate");
                    String tableId = (String) jObject.get("table");
                    JSONArray table = (JSONArray) jObject.get("rates");
                    for (Object ob2 : table) {

                        Currency currency = new Currency();

                        JSONObject job = (JSONObject) ob2;

                        currency.setCode((String) job.get("code"));
                        currency.setMid((Double) job.get("mid"));
                        currency.setCurrency((String) job.get("currency"));
                        currency.setTableId(tableId);
                        currency.setEffectiveDate(effectiveDate);

                        currencyList.add(currency);
                    }

                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }

    public Table() throws IOException {
        setTable();
    }

}
