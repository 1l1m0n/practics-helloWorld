package com.antonovproject.hello;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;


public interface UserRepo extends JpaRepository<User, Long>{

    Page<User> findByFirstNameIn(List<String> firstName, Pageable pageable);

    Page<User> findByLastNameIn(List<String> lastName, Pageable pageable);

    Page<User> findByIdIn(List<Long> id, Pageable pageable);

    @Modifying
    @Transactional
    @Query("update User u set u.firstName = :firstName where u.id = :id")
    int updateFirstNameById(Long id, String firstName);

    @Modifying
    @Transactional
    @Query("update User u set u.lastName = :lastName where u.id = :id")
    int updateLastNameById(Long id, String lastName);
}
