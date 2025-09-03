package com.antonovPractics.demoProject.repository;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class TowarMagazyn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "towar_id")
    private Towar towar;

    @ManyToOne
    @JoinColumn(name = "magazyn_id")
    private Magazyn magazyn;

    private int ilosc;

    private LocalDateTime lastEdit;
}
