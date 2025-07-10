package com.antonovproject.hello;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Long>{

    Page<User> findByNameIn(List<String> name, Pageable pageable);
}
