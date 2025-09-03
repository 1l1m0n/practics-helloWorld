package com.antonovPractics.demoProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antonovPractics.demoProject.repository.Kategoria;
import com.antonovPractics.demoProject.repository.KategoriaRepository;

@Service
public class KategoriaService {

    public List<Kategoria> getRootKategorie() {
        return kategoriaRepository.findByKategoriaNadrzednaIsNull();
    }

    @Autowired
    private KategoriaRepository kategoriaRepository;

    public Kategoria addKategoria(Kategoria kategoria) {
        if (kategoria.getKategoriaNadrzedna() != null && kategoria.getKategoriaNadrzedna().getId() != null) {
            Kategoria kategoriaNadrzedna = kategoriaRepository.findById(kategoria.getKategoriaNadrzedna().getId())
                    .orElse(null);
            kategoria.setKategoriaNadrzedna(kategoriaNadrzedna);
        }
        return kategoriaRepository.save(kategoria);
    }

    public List<Kategoria> getAllKategorie() {
        return kategoriaRepository.findAll();
    }

    public Kategoria getKategoria(Long id) {
        return kategoriaRepository.findById(id).orElse(null);
    }

    public Kategoria editKategoria(Long id, Kategoria kategoria) {
        Kategoria dbKategoria = kategoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Kategorii o id " + id + " nie istnieje"));

        if (kategoria.getName() != null && !kategoria.getName().equals(dbKategoria.getName())) {
            dbKategoria.setName(kategoria.getName());
        }
        return kategoriaRepository.save(dbKategoria);
    }

    public void deleteKategoria(Long id) {
        kategoriaRepository.deleteById(id);
    }
}