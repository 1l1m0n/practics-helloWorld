package com.antonovproject.hello;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class HelloController {

    @Autowired
    private UserRepo userRepo;
    
    @RequestMapping("/helloWorld")
    public String helloWorld() {
        return "Hello Antonov!!!!!";
    }

    @PostMapping("/addUser")
    public String addUser(@RequestParam String firstName, @RequestParam(required=false) String lastName) {

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        userRepo.save(user);
        return "User added and save: " + firstName + " " + lastName;
    }

    @PostMapping("/generateUsers")
    public String generateUsers() {
        for (int i = 0; i < 500; i++) {
            User user = new User();
            user.setFirstName("user" + i);
            user.setLastName("lastName" + i);
            userRepo.save(user);
        }
        return "500 users added to DB";
    }
    
    @GetMapping("/getUsers")
    public Page<User> getUsers(@RequestParam(required = false) List<String> firstName,
            @RequestParam(required = false) List<String> lastName, @RequestParam(required = false) List<Long> id, Pageable pageable) {
        if (firstName != null && !firstName.isEmpty()) {
            return userRepo.findByFirstNameIn(firstName, pageable);
        } else if (lastName != null && !lastName.isEmpty()) {
            return userRepo.findByLastNameIn(lastName, pageable);
        } else if (id != null && !id.isEmpty()) {
            return userRepo.findByIdIn(id, pageable);
        } else {
            return userRepo.findAll(pageable);
        }
    }

    @PostMapping("/editUser")
    public String editUser(@RequestParam Long id, @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {

        int updated = 0;

        if (userRepo.findById(id).isEmpty()) {
            return "User " + id + " not found";
        }

        if (firstName != null) {
            updated += userRepo.updateFirstNameById(id, firstName);
        }
        if (lastName != null) {
            updated += userRepo.updateLastNameById(id, lastName);
        }
        if (updated > 0) {
            return "User updated: " + id;
        } else {
            return "Nothing to update";
        }
    }

    @DeleteMapping("/deleteUsers")
    public String deleteUsers(@RequestParam List<Long> id) {
        userRepo.deleteAllByIdInBatch(id);
        return "Deleted users: " + id;
    }

    @DeleteMapping("/deleteAllUsers")
    public String deleteAllUsers() {
        userRepo.deleteAll();
        return "All users deleted";
    }
}
