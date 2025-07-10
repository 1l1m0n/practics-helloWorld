package com.antonovproject.hello;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/addUser")
    public String addUser(@RequestParam String name) {

        User user = new User();
        user.setName(name);
        userRepo.save(user);
        return "User added and save: " + name;
    }

    @GetMapping("/generateUsers")
    public String generateUsers() {
        for (int i = 0; i < 500; i++) {
            User user = new User();
            user.setName("user" + i);
            userRepo.save(user);
        }
        return "500 users added to DB";
    }
    
    @GetMapping("/getUsers")
    public Page<User> getUsers(@RequestParam(required = false) List<String> name, Pageable pageable) {
        if (name != null && !name.isEmpty()) {
            return userRepo.findByNameIn(name, pageable);
        } else {
            return userRepo.findAll(pageable);
        }
    }
}
