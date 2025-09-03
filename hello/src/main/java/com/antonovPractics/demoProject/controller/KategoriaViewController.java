package com.antonovPractics.demoProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.antonovPractics.demoProject.repository.Kategoria;
import com.antonovPractics.demoProject.service.KategoriaService;

@Controller()
public class KategoriaViewController {

    @Autowired
    private KategoriaService kategoriaService;

    @GetMapping("/kategorie")
    public String getKategorieTree(Model model) {
        List<Kategoria> rootKategorie = kategoriaService.getRootKategorie();
        model.addAttribute("kategorie", rootKategorie);
        return "kategorie-tree";
    }

    //todo: dodac wyszukiwanie kategorii
    //todo: dodac edycje kategorii
}
