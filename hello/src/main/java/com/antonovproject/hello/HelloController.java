package com.antonovproject.hello;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class HelloController {
    
    @RequestMapping("/helloWorld")
    public String helloWorld() {
        return "Hello Antonov!!!!!";
    }

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/addUser")
    public String addUser(@RequestParam String name) {

        User user = new User();
        user.setName(name);
        userRepo.save(user);
        return "User added and save: " + name;
    }
    
    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return userRepo.findAll();
    }
}
