package com.antonovPractics.demoProject.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

    Page<User> findByFirstNameIn(List<String> firstName, Pageable pageable);

    Page<User> findByLastNameIn(List<String> lastName, Pageable pageable);

    Page<User> findByIdIn(List<Long> id, Pageable pageable);
}
