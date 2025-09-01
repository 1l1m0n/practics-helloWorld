package com.antonovPractics.demoProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.antonovPractics.demoProject.repository.Kategoria;
import com.antonovPractics.demoProject.service.KategoriaService;

@RestController
@RequestMapping("api/kategoria")
public class KategoriaConroller {

    @Autowired
    private KategoriaService kategoriaService;

    @PostMapping("/addKategoria")
    public Kategoria addKategoria(@RequestBody Kategoria kategoria) {
        return kategoriaService.addKategoria(kategoria);
    }

    @GetMapping
    public List<Kategoria> getAllKategorie() {
        return kategoriaService.getAllKategorie();
    }

    @GetMapping("/{id}")
    public Kategoria getKategoria(@PathVariable Long id) {
        return kategoriaService.getKategoria(id);
    }

    @PutMapping("/edit/{id}")
    public Kategoria editKategoria(@PathVariable Long id, @RequestBody Kategoria kategoria) {
        return kategoriaService.editKategoria(id, kategoria);
    }

    //todo : dodac wyswietlanie z paginacja

    @DeleteMapping("/delete/{id}")
    public void deleteKategoria(@PathVariable Long id) {
        kategoriaService.deleteKategoria(id);
    }
}
