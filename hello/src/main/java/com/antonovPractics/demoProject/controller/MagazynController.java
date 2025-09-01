package com.antonovPractics.demoProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.antonovPractics.demoProject.repository.Magazyn;
import com.antonovPractics.demoProject.service.MagazynService;

@RestController
@RequestMapping("api/magazyn")
public class MagazynController {

    @Autowired
    private MagazynService magazynService;

    @PostMapping("/addMagazyn")
    public Magazyn addMagazyn(@RequestBody Magazyn magazyn) {
        return magazynService.addMagazyn(magazyn);
    }

    @GetMapping
    public List<Magazyn> getAllMagazyny() {
        return magazynService.getAllMagazyny();
    }

    @GetMapping("/{id}")
    public Magazyn getMagazyn(@PathVariable Long id) {
        return magazynService.getMagazyn(id);
    }

    @PutMapping("/edit/{id}")
    public Magazyn editMagazyn(@PathVariable Long id, @RequestBody Magazyn magazyn) {
        return magazynService.editMagazyn(id, magazyn);
    }

    @GetMapping("/search")
    public Page<Magazyn> getMagazyny(Pageable pageable, @RequestParam(required = false) List <Long> id) {
        return magazynService.getMagazyny(id, pageable);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMagazyn(@PathVariable Long id) {
        magazynService.deleteMagazyn(id);
    }
}
