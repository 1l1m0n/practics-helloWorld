package com.antonovPractics.demoProject.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MagazynRepository extends JpaRepository<Magazyn, Long>{

    Page<Magazyn> findByIdIn(List<Long> id, Pageable pageable);

    Page<Magazyn> findAll(Pageable pageable);

}
