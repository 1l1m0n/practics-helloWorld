package com.antonovPractics.demoProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.antonovPractics.demoProject.repository.Kategoria;
import com.antonovPractics.demoProject.repository.Towar;
import com.antonovPractics.demoProject.service.KategoriaService;
import com.antonovPractics.demoProject.service.TowarService;

@Controller
public class TowarViewController {

    @Autowired
    private TowarService towarService;

    @Autowired
    private KategoriaService kategoriaService;

    @GetMapping("/towary")
    public String getTowary(Model model) {
        Iterable<Towar> towary = towarService.getAllTowary();
        model.addAttribute("towary", towary);
        return "towary";
    }

    @GetMapping("/towary/addTowar")
    public String addTowar(Model model) {
        List<Kategoria> kategorie = kategoriaService.getRootKategorie();
        model.addAttribute("kategorie", kategorie);
        return "add-towar";
    }
    
    @PostMapping("/towary/addTowar")
    public String addTowarSubmit(@RequestParam String name, @RequestParam String opis, @RequestParam Long kategoriaId,
            Model model) {
        towarService.addTowar(name, opis, kategoriaId);
        return "redirect:/towary";
    }
    
    // todo: sprawdzic dlaczego tu jest inaczej od usera
    @GetMapping("/towar/{id}")
    public String towarProfile(@PathVariable Long id, Model model) {
        Towar towar = towarService.getTowar(id);
        List<Kategoria> kategorie = kategoriaService.getRootKategorie();
        model.addAttribute("towar", towar);
        model.addAttribute("kategorie", kategorie);
        return "towar-profile";
    }

    @GetMapping("/towar/{id}/edit")
    public String editTowar(@PathVariable Long id, Model model) {
        Towar towar = towarService.getTowar(id);
        List<Kategoria> kategorie = kategoriaService.getRootKategorie();
        model.addAttribute("towar", towar);
        model.addAttribute("kategorie", kategorie);
        return "towar-edit";
    }

    @PostMapping("/towar/{id}/edit")
    public String towarUpdate(@PathVariable Long id, @RequestParam String name, @RequestParam String opis,
            @RequestParam Long kategoriaId) {
        towarService.towarUpdate(id, name, opis, kategoriaId);
        return "redirect:/towar/" + id;
    }
    
    //todo: dodac usuwanie towaru oraz dodac przycisk do tego na html
}
