package com.waluty.controllers;

import com.waluty.model.Currency;
import com.waluty.model.dto.CurrencyDto;
import com.waluty.repository.CurrencyRepository;
import com.waluty.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/currency")
public class CurrencyController{
    private CurrencyRepository currencyRepository;
    private CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyRepository currencyRepository, CurrencyService currencyService) {
        this.currencyRepository = currencyRepository;
        this.currencyService = currencyService;
    }

    @GetMapping("/selectAll")
    public String getCurrency(Model model){


        model.addAttribute("currencies", currencyService.getAllProducts());
        return "currency";
    }

    @GetMapping("/insert")
    public String currencyInsert(Model model){
        model.addAttribute("currency", new Currency());
        return "currencyInsert";
    }

    @PostMapping("/insert")
    public String currencyInsertPost(@ModelAttribute CurrencyDto currency){

        currencyService.addOrUpdateCurrency(currency);

        return "redirect:/currency/selectAll";
    }

    @GetMapping("/update/{id}")
    public String currencyUpdate(Model model, @PathVariable Long id){
        model.addAttribute("currency", currencyService.getOneCurrency(id).orElseThrow(NullPointerException::new));

        return "update";
    }

    @PostMapping("/update")
    public String currencyUpdatePost(@ModelAttribute CurrencyDto currency){
        currencyService.addOrUpdateCurrency(currency);
        return "redirect:/currency/selectAll";
    }

    @GetMapping("/remove/{id}")
    public String personRemovetGet(@PathVariable Long id,  Model model)
    {
        currencyService.removeCurrency(id);
        return "redirect:/currency/selectAll";
    }


}