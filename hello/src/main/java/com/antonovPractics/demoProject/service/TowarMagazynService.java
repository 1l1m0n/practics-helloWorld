package com.antonovPractics.demoProject.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antonovPractics.demoProject.repository.Magazyn;
import com.antonovPractics.demoProject.repository.MagazynRepository;
import com.antonovPractics.demoProject.repository.Towar;
import com.antonovPractics.demoProject.repository.TowarMagazyn;
import com.antonovPractics.demoProject.repository.TowarMagazynRepository;
import com.antonovPractics.demoProject.repository.TowarRepository;

@Service
public class TowarMagazynService {
    @Autowired
    private TowarMagazynRepository towarMagazynRepository;
    @Autowired
    private TowarRepository towarRepository;
    @Autowired
    private MagazynRepository magazynRepository;

    public List<TowarMagazyn> getAll() {
        return towarMagazynRepository.findAll();
    }

    public List<TowarMagazyn> getByMagazyn(Long id) {
        return towarMagazynRepository.findByMagazyn_Id(id);
    }

    public void transferTowar(Long towarId, Long fromMagazynId, Long toMagazynId, int ilosc) {
        TowarMagazyn from = towarMagazynRepository.findByTowar_IdAndMagazyn_Id(towarId, fromMagazynId);
        TowarMagazyn to = towarMagazynRepository.findByTowar_IdAndMagazyn_Id(towarId, toMagazynId);
        if (from == null || from.getIlosc() < ilosc)
            throw new IllegalStateException("Brak wystarczającej ilości towaru");
        from.setIlosc(from.getIlosc() - ilosc);
        from.setLastEdit(LocalDateTime.now());
        if (to == null) {
            to = new TowarMagazyn();
            to.setTowar(from.getTowar());
            to.setMagazyn(magazynRepository.findById(toMagazynId).orElseThrow());
            to.setIlosc(ilosc);
            to.setLastEdit(LocalDateTime.now());
        } else {
            to.setIlosc(to.getIlosc() + ilosc);
            to.setLastEdit(LocalDateTime.now());
        }
        towarMagazynRepository.save(from);
        towarMagazynRepository.save(to);
    }
    
    public void addOrUpdate(Long id, Long towarId, int ilosc) {
        TowarMagazyn towarMagazyn = towarMagazynRepository.findByTowar_IdAndMagazyn_Id(towarId, id);
        Towar towar = towarRepository.findById(towarId)
                .orElseThrow(() -> new IllegalStateException("Towar o id " + towarId + " nie istnieje"));
        Magazyn magazyn = magazynRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Magazyn o id " + id + " nie istnieje"));
        if (towarMagazyn == null) {
            towarMagazyn = new TowarMagazyn();
            towarMagazyn.setTowar(towar);
            towarMagazyn.setMagazyn(magazyn);
            towarMagazyn.setIlosc(ilosc);
            towarMagazyn.setLastEdit(LocalDateTime.now());
        } else {
            towarMagazyn.setIlosc(towarMagazyn.getIlosc() + ilosc);
            towarMagazyn.setLastEdit(LocalDateTime.now());
        }
        towarMagazynRepository.save(towarMagazyn);
    }
}