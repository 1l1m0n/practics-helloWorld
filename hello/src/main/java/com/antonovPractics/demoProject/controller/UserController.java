package com.antonovPractics.demoProject.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.antonovPractics.demoProject.repository.User;
import com.antonovPractics.demoProject.repository.UserRepository;
import com.antonovPractics.demoProject.service.UserService;



@RestController
@RequestMapping(path = "api/users")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserService userService;

    ///////////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping("/helloWorld")
    public String helloWorld() {
        return userService.helloWorld();
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping("/generateUsers")
    public String generateUsers() {
        return userService.generateUsers();
    }
    
    @GetMapping("/getUsers")
    public Page<User> getUsers(@RequestParam(required = false) List<String> firstName,
            @RequestParam(required = false) List<String> lastName, @RequestParam(required = false) List<Long> id, Pageable pageable) {
        return userService.getUsers(firstName, lastName, id, pageable);
    }

    @PutMapping("/editUser")
    public String editUser(@RequestBody User user) {
        return userService.editUser(user);
    }

    @DeleteMapping("/deleteUsers")
    public String deleteUsers(@RequestParam List<Long> id) {
        return userService.deleteUsers(id);
    }

    @DeleteMapping("/deleteAllUsers")
    public String deleteAllUsers() {
        return userService.deleteAllUsers();
    }
}
