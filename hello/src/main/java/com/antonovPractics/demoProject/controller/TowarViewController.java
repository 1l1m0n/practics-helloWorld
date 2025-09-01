package com.antonovPractics.demoProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.antonovPractics.demoProject.repository.Towar;
import com.antonovPractics.demoProject.service.TowarService;

@Controller
public class TowarViewController {

    @Autowired
    private TowarService towarService;

    @GetMapping("/towary")
    public String getTowary(Model model) {
        Iterable<Towar> towary = towarService.getAllTowary();
        model.addAttribute("towary", towary);
        return "towary";
    }

    @GetMapping("/towary/addTowar")
    public String addTowar(Model model) {
        return "add-towar";
    }
    
    @PostMapping("/towary/addTowar")
    public String addTowarSubmit(@RequestParam String name, @RequestParam String opis, Model model) {
        towarService.addTowar(name, opis);
        return "redirect:/towary";
    }
}
