package com.antonovPractics.demoProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface KategoriaRepository extends JpaRepository<Kategoria, Long>{
	List<Kategoria> findByKategoriaNadrzednaIsNull();

}
