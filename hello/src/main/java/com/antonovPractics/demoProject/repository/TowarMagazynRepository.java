package com.antonovPractics.demoProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TowarMagazynRepository extends JpaRepository<TowarMagazyn, Long> {
    List<TowarMagazyn> findByMagazyn_Id(Long magazynId);
    List<TowarMagazyn> findByTowar_Id(Long towarId);
    TowarMagazyn findByTowar_IdAndMagazyn_Id(Long towarId, Long magazynId);
}
