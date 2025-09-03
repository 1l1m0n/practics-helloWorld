package com.antonovPractics.demoProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.antonovPractics.demoProject.service.TowarMagazynService;

@Controller
public class TowarMagazynViewController {
    @Autowired
    private TowarMagazynService towarMagazynService;

    @GetMapping("/towarMagazyn")
    public String getAll(Model model) {
        model.addAttribute("towarMagazynList", towarMagazynService.getAll());
        return "towar-magazyn-list";
    }

    @PostMapping("/towarMagazyn/transfer")
    public String transferTowar(@RequestParam Long towarId, @RequestParam Long fromMagazynId, @RequestParam Long toMagazynId, @RequestParam int ilosc) {
        towarMagazynService.transferTowar(towarId, fromMagazynId, toMagazynId, ilosc);
        return "redirect:/towarMagazyn";
    }
}