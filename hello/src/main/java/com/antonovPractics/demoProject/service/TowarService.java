package com.antonovPractics.demoProject.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.antonovPractics.demoProject.repository.Kategoria;
import com.antonovPractics.demoProject.repository.KategoriaRepository;
import com.antonovPractics.demoProject.repository.Towar;
import com.antonovPractics.demoProject.repository.TowarRepository;

@Service
public class TowarService {

    @Autowired
    private TowarRepository towarRepository;

    @Autowired
    private KategoriaRepository kategoriaRepository;

    public Towar addTowar(Towar towar) {
        if (towar.getDateOfAdd() == null) {
            towar.setDateOfAdd(LocalDate.now());
        }
        towar.setDateOfLastEdit(LocalDateTime.now());

        if (towar.getKategoria() != null && towar.getKategoria().getId() != null) {
            Kategoria kategoria = kategoriaRepository.findById(towar.getKategoria().getId()).orElse(null);
            towar.setKategoria(kategoria);
        }
        return towarRepository.save(towar);
    }
    
    public Towar addTowar(String name, String opis, Long kategoriaId) {
        Towar towar = new Towar();
        towar.setName(name);
        towar.setOpis(opis);
        towar.setDateOfAdd(LocalDate.now());
        towar.setDateOfLastEdit(LocalDateTime.now());
        Kategoria kategoria = kategoriaRepository.findById(kategoriaId).orElseThrow(() -> new IllegalStateException("Kategoria o id " + kategoriaId + " nie istnieje"));
        towar.setKategoria(kategoria);
        return towarRepository.save(towar);
    }

    public List<Towar> getAllTowary() {
        return towarRepository.findAll();
    }

    public Towar getTowar(Long id) {
        return towarRepository.findById(id).orElse(null);
    }

    public Page<Towar> getTowar(List<String> name, List<Long> id, Pageable pageable) {
        if (name != null && !name.isEmpty()) {
            return towarRepository.findByNameIn(name, pageable);
        } else if (id != null && !id.isEmpty()) {
            return towarRepository.findByIdIn(id, pageable);
        } else {
            return towarRepository.findAll(pageable);
        }
    }

    public Towar editTowar(Long id, Towar towar) {
        Towar dbTowar = towarRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Towar o id " + id + " nie istnieje"));

        if (towar.getName() != null && !towar.getName().equals(dbTowar.getName())) {
            dbTowar.setName(towar.getName());
        }
        if (towar.getOpis() != null && !towar.getOpis().equals(dbTowar.getOpis())) {
            dbTowar.setOpis(towar.getOpis());
        }
        if (towar.getKategoria() != null && !towar.getKategoria().equals(dbTowar.getKategoria())) {
            dbTowar.setKategoria(towar.getKategoria());
        }

        //todo zrobic zeby czas wyswietlał sie nie tak szczegołowo
        dbTowar.setDateOfLastEdit(LocalDateTime.now());
        return towarRepository.save(dbTowar);
    }

    public Towar towarUpdate(Long id, String name, String opis, Long kategoriaId) {
        Towar towar = towarRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Towar o id " + id + " nie istnieje"));
        Kategoria kategoria = kategoriaRepository.findById(kategoriaId).orElseThrow(() -> new IllegalStateException("Kategoria o id " + kategoriaId + " nie istnieje"));
        towar.setName(name);
        towar.setOpis(opis);
        towar.setKategoria(kategoria);
        towar.setDateOfLastEdit(LocalDateTime.now());
        return towarRepository.save(towar);
    }

    public void deleteTowar(Long id) {
        towarRepository.deleteById(id);
    }

    public void deleteAllTowary() {
        towarRepository.deleteAll();
    }
}
