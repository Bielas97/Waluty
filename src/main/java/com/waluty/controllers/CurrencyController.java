package com.waluty.controllers;

import com.waluty.model.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@requestmapping("/Currency")
public class CurrencyController{

    @GetMapping("/getAll")
    public String getCurrency(Model model){
        model.addAttribute("currency", Currency.values());

        return "getAll";
    }


}