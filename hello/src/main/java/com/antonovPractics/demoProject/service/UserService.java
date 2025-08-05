package com.antonovPractics.demoProject.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.antonovPractics.demoProject.repository.User;
import com.antonovPractics.demoProject.repository.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public String helloWorld() {
        return "Hello Antonov!!!!!";
    }

    public String generateUsers() {
        for (int i = 0; i < 500; i++) {
            User user = new User();
            user.setFirstName("user" + i);
            user.setLastName("lastName" + i);
            userRepository.save(user);
        }
        return "500 users added to DB";
    }

    public User addUser(User user) {

        user.setAge(Period.between(user.getBirthDate(), LocalDate.now()).getYears());
        return userRepository.save(user);
    }

    public Page<User> getUsers(List<String> firstName, List<String> lastName, List<Long> id, Pageable pageable) {

        if (firstName != null && !firstName.isEmpty()) {
            return userRepository.findByFirstNameIn(firstName, pageable);
        } else if (lastName != null && !lastName.isEmpty()) {
            return userRepository.findByLastNameIn(lastName, pageable);
        } else if (id != null && !id.isEmpty()) {
            return userRepository.findByIdIn(id, pageable);
        } else {
            return userRepository.findAll(pageable);
        }
    }

    @Transactional
    public String editUser(User user) {

        Long id = user.getId();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        LocalDate birthDate = user.getBirthDate();

        User dbUser = userRepository.findById(id)
        .orElseThrow(() -> new IllegalStateException("User with id: " + id + " does not exist"));

        boolean changed = false;

        if (firstName != null && !firstName.equals(dbUser.getFirstName())) {
            dbUser.setFirstName(firstName);
            changed = true;
        }
        if (lastName != null && !lastName.equals(dbUser.getLastName())) {
            dbUser.setLastName(lastName);
            changed = true;
        }
        if (birthDate != null && !birthDate.equals(dbUser.getBirthDate())) {
            dbUser.setBirthDate(birthDate);
            dbUser.setAge(Period.between(dbUser.getBirthDate(), LocalDate.now()).getYears());
            changed = true;
        }
        if (changed) {
            return "User " + id + " updated";
        } else {
            return "Nothing to update";
        }
    }
    
    public String deleteUsers(List<Long> id) {

        List<User> listOfUsers = userRepository.findAllById(id);
        if (listOfUsers.isEmpty()) {
            throw new IllegalStateException("Users does not exists");
        }

        userRepository.deleteAllByIdInBatch(id);
        return "Deleted users: " + id;
    }

    public String deleteAllUsers() {
        userRepository.deleteAll();
        return "All users deleted";
    }
}
