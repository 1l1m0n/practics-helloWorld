package com.antonovPractics.demoProject.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Towar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String opis;
    
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfAdd;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime dateOfLastEdit;

    @ManyToOne
    @JoinColumn(name = "kategoria_id")
    private Kategoria kategoria;

}
