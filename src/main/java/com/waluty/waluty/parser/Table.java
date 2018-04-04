package com.waluty.Parser;

import com.fasterxml.jackson.core.JsonParser;
import com.waluty.waluty.Currency;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.simple.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.ZoneId;



public class Table {
    private String NBP = "http://api.nbp.pl/api/exchangerates/tables/A/" +;

    private String datePublication;


    private void setTable() {
        try {
            java.net.URL url = new URL(NBP);
            URLConnection connection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            JSONParser Parser = new JsonParser();
            String napis = "";
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
                            Currency currency = new Currency((String) job.get("code"), (String) job.get("currency"), (double) job.get("mid"));
                            List<Currency> list = new ArrayList<Currency>();
                            list.add(currency);
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
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
        this.NBP = "http://api.nbp.pl/api/exchangerates/tables/A/" + time.format(formatter);
    }

    public Table(Date date) {
        LocalDate data = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        this.NBP = "http://api.nbp.pl/api/exchangerates/tables/A/" + data.getYear() + " " + data.getMonthValue() + " " + data.getDayOfMonth();
    }
}
