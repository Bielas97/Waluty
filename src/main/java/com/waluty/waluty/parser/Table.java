package com.waluty.waluty.parser;

import com.waluty.waluty.Currency;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class Table {
    static final private String NBP = "http://api.nbp.pl/api/exchangerates/tables/A/";

    private String datePublication;

    private List<Currency> listCourrency;

    private void setTable() {
        try {
            java.net.URL url = new URL(NBP);
            URLConnection connection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            JSONParser jsonParser = new JSONParser();
            String napis = "";
            listCourrency = new ArrayList<>();
            while ((napis = bufferedReader.readLine()) != null) {
                try {
                    JSONArray tab = (JSONArray) jsonParser.parse(napis);
                    for (Object obiect : tab) {
                        JSONObject jO = (JSONObject) obiect;
                        JSONArray table = (JSONArray) jO.get("rates");
                        if (datePublication == null) {
                            datePublication = (String) jO.get("effectiveDate");
                        }
                        for (Object obiect2 : table) {
                            JSONObject job = (JSONObject) obiect2;
                            //ustawiamy pola skladowe Currency
                            listCourrency.add(currency);
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Table() {
        this.setTable();
    }
}
