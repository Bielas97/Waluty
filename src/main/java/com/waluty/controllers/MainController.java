package com.waluty.controllers;

import com.waluty.service.CurrencyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private CurrencyService currencyService;

    public MainController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/")
    public String mainPage(){

        return "index";
    }
}
