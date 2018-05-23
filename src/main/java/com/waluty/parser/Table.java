package com.waluty.parser;


import com.waluty.model.Currency;
import com.waluty.service.CurrencyServiceImp;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@Component
public class Table {
    private static final String NBP = "http://api.nbp.pl/api/exchangerates/tables/A/";
    private List<Currency> currencyList = new ArrayList<>();


    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    private final CurrencyServiceImp currencyService;

    public Table(CurrencyServiceImp currencyService) {
        this.currencyService = currencyService;
        try {
            setTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Currency setUPCurrency(String code, Double mid, String currencyName, String tableId, String effectiveDate) {
        Currency currency = new Currency();

        currency.setCode(code);
        currency.setMid(mid);
        currency.setCurrency(currencyName);
        currency.setTableId(tableId);
        currency.setEffectiveDate(effectiveDate);

        return currency;
    }

    private void parseJSON(BufferedReader bufferedReader) throws IOException {
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

                        JSONObject job = (JSONObject) ob2;

                        Currency currency = setUPCurrency((String) job.get("code"), (Double) job.get("mid"), (String) job.get("currency"), tableId, effectiveDate);

                        currencyService.addOrUpdateCurrency(currency);

                        currencyList.add(currency);
                    }

                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private void setTable() throws IOException {

        java.net.URL url = new URL(NBP);
        URLConnection urlConnection = url.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        parseJSON(bufferedReader);

    }


}
