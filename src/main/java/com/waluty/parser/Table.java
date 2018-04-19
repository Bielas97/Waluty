package com.waluty.parser;

import com.waluty.model.Currency;
import com.waluty.model.dto.ConverterDto;
import com.waluty.model.dto.CurrencyDto;
import com.waluty.repository.CurrencyRepository;
import lombok.Data;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@Data
public class Table {
    static final private String NBP = "http://api.nbp.pl/api/exchangerates/tables/A/";

    private ConverterDto converterDto;
    private CurrencyRepository currencyRepository;

    public Table(ConverterDto converterDto, CurrencyRepository currencyRepository) {
        this.converterDto = converterDto;
        this.currencyRepository = currencyRepository;
    }

    private String datePublication;

    private List<CurrencyDto> listCurrency;

    private void setTable() {
        try {
            java.net.URL url = new URL(NBP);
            URLConnection connection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            org.json.simple.parser.JSONParser jsonParser = new org.json.simple.parser.JSONParser();
            String napis = "";
            listCurrency = new ArrayList<>();
            while ((napis = bufferedReader.readLine()) != null) {
                try {
                    JSONArray tab = (JSONArray) jsonParser.parse(napis);
                    for (Object obiect : tab) {
                        JSONObject jO = (JSONObject) obiect;
                        //System.out.println("XXXXXXXXXXXXXXXXX________________--------------------");
                        // System.out.println(jO.toString());

                        JSONArray table = (JSONArray) jO.get("rates");
                        if (datePublication == null) {
                            datePublication = (String) jO.get("effectiveDate");
                        }
                        for (Object obiect2 : table) {
                            JSONObject job = (JSONObject) obiect2;
                            CurrencyDto currency = new CurrencyDto();

                            currency.setCode((String) job.get("code"));
                            currency.setMid((Double) job.get("mid"));
                            currency.setCurrency((String) job.get("currency"));
                            //niepotrzene!!!!
                       /*     //nie jestem pewien moze byc do poprawy ale idzie do mastera
                            currency.setTable((String) job.get("table"));
                            currency.setNo((Double) job.get("no"));
                            //koniec fragmentu ktorego nie jestem pewien*/
                            listCurrency.add(currency);
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

    //metoda dodajaca kazda pozycje z listCurrency do bazy danych
    //by metoda poprawnie zapisywala do bazy trzeba skorzystac z metody fromCurrencyDtoToCurrency!!!!!
    //aby tego dokonac ConverterDto musi byc wstrzyknietym beanem!!!!
    private void saveListCurrencytoDb(){
        for (CurrencyDto c : listCurrency){
            currencyRepository.save(converterDto.fromCurrencyDtoToCurrency(c));
        }
    }

    public Table() {
        this.setTable();
        saveListCurrencytoDb();
        //wywolanie metody dodajaca kazda pozycje z listCurrency do bazy danych
    }
}