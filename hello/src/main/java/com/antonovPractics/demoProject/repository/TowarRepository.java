package com.antonovPractics.demoProject.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TowarRepository extends JpaRepository<Towar, Long>{

    Page<Towar> findByNameIn(List<String> name, Pageable pageable);
    
    Page<Towar> findByIdIn(List<Long> id, Pageable pageable);
}
