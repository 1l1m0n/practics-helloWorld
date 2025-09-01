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

import com.antonovPractics.demoProject.repository.Towar;
import com.antonovPractics.demoProject.service.TowarService;

@RestController
@RequestMapping("api/towar")
public class TowarController {

    @Autowired
    private TowarService towarService;

    @PostMapping("/addTowar")
    public Towar addTowar(@RequestBody Towar towar) {
        return towarService.addTowar(towar);
    }

    @GetMapping("/{id}")
    public Towar getTowar(@PathVariable Long id) {
        return towarService.getTowar(id);
    }

    @GetMapping("/getTowary")
    public Page<Towar> getTowar(@RequestParam(required = false) List<String> name, @RequestParam(required = false) List<Long> id, Pageable pageable) {
        return towarService.getTowar(name, id, pageable);
    }

    @PutMapping("/edit/{id}")
    public Towar editTowar(@PathVariable Long id, @RequestBody Towar towar) {
        return towarService.editTowar(id, towar);
    }

    @DeleteMapping("delete/{id}")
    public void deleteTowar(@PathVariable Long id) {
        towarService.deleteTowar(id);
    }

    public void deleteAllTowary() {
        towarService.deleteAllTowary();
    }
}
