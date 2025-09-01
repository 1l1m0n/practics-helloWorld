package com.antonovPractics.demoProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.antonovPractics.demoProject.repository.Magazyn;
import com.antonovPractics.demoProject.repository.MagazynRepository;

@Service
public class MagazynService {
    
    @Autowired
    private MagazynRepository magazynRepository;

    public Magazyn addMagazyn(Magazyn magazyn) {
        return magazynRepository.save(magazyn);
    }

    public List<Magazyn> getAllMagazyny() {
        return magazynRepository.findAll();
    }

    public Magazyn getMagazyn(Long id) {
        return magazynRepository.findById(id).orElse(null);
    }

    public Magazyn editMagazyn(Long id, Magazyn magazyn) {
        Magazyn dbMagazyn = magazynRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Magazyn o id " + id + " nie istnieje"));

        if (magazyn.getName() != null && !magazyn.getName().equals(dbMagazyn.getName())) {
            dbMagazyn.setName(magazyn.getName());
        }
        return magazynRepository.save(dbMagazyn);
    }
    
    public Page<Magazyn> getMagazyny(List<Long> id, Pageable pageable) {
        if (id != null && !id.isEmpty()) {
            return magazynRepository.findByIdIn(id, pageable);
        } else {
            return magazynRepository.findAll(pageable);
        }
    }

    public void deleteMagazyn(Long id) {
        magazynRepository.deleteById(id);
    }
}
