package com.antonovPractics.demoProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.antonovPractics.demoProject.repository.Magazyn;
import com.antonovPractics.demoProject.repository.Towar;
import com.antonovPractics.demoProject.repository.TowarMagazyn;
import com.antonovPractics.demoProject.repository.TowarRepository;
import com.antonovPractics.demoProject.service.MagazynService;
import com.antonovPractics.demoProject.service.TowarMagazynService;

@Controller
public class MagazynViewController {

    @Autowired
    private MagazynService magazynService;

    @Autowired
    private TowarMagazynService towarMagazynService;

    @Autowired
    private TowarRepository towarRepository;

    @GetMapping("/magazyny")
    public String getMagazyny(Model model) {
        List<Magazyn> magazyny = magazynService.getAllMagazyny();
        model.addAttribute("magazyny", magazyny);
        return "magazyny";
    }

    @GetMapping("/magazyn/{id}")
    public String magazynProfile(@PathVariable Long id, Model model) {
        Magazyn magazyn = magazynService.getMagazyn(id);
        List<TowarMagazyn> towary = towarMagazynService.getByMagazyn(id);
        List<Towar> wszystkieTowary = towarRepository.findAll();
        List<Magazyn> wszystkieMagazyny = magazynService.getAllMagazyny();
        model.addAttribute("magazyn", magazyn);
        model.addAttribute("towary", towary);
        model.addAttribute("wszystkieTowary", wszystkieTowary);
        model.addAttribute("wszystkieMagazyny", wszystkieMagazyny);
        return "magazyn-profile";
    }

    @PostMapping("/magazyn/{id}/transfer")
    public String transferTowar(@PathVariable Long id, @RequestParam Long towarId, @RequestParam int ilosc,
            @RequestParam Long docelowyMagazynId, Model model) {
        try {
            towarMagazynService.transferTowar(towarId, id, docelowyMagazynId, ilosc);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/magazyn/" + id;
    }
    
    @PostMapping("/magazyn/{id}/addTowarDoMagazynu")
    public String addTowarDoMagazynu(@PathVariable Long id, @RequestParam Long towarId, @RequestParam int ilosc) {
        towarMagazynService.addOrUpdate(id, towarId, ilosc);
        return "redirect:/magazyn/" + id;
    }
}
