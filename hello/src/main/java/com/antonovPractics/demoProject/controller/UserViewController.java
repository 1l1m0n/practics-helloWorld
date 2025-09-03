package com.antonovPractics.demoProject.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.antonovPractics.demoProject.repository.User;
import com.antonovPractics.demoProject.service.UserService;

@Controller
public class UserViewController {

    @Autowired
    private UserService userService;

    @GetMapping("get-home")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        Iterable<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "get-users";
    }

    @GetMapping("/users/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    //todo: zmienic pozniej na to jak w dodawaniu towaru
    @PostMapping("/users/add")
    public String addUserSubmit(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/user/{id}")
    public String userProfile(@PathVariable Long id, Model model) {

        ArrayList<User> res = new ArrayList<>();
        userService.getUser(id).ifPresent(res::add);
        model.addAttribute("user", res);
        return "user-profile";
    }
    
    @GetMapping("/user/{id}/edit")
    public String userEdit(@PathVariable Long id, Model model) {
        
        ArrayList<User> res = new ArrayList<>();
        userService.getUser(id).ifPresent(res::add);
        model.addAttribute("user", res);
        return "user-edit";
    }

    //todo: dodac edycje daty urodzenia
    @PostMapping("/user/{id}/edit")
    public String userUpdate(@PathVariable Long id, @RequestParam String firstName, @RequestParam String lastName, Model model) {
        userService.userUpdate(id, firstName, lastName);
        return "redirect:/users";
    }

    @PostMapping("/user/{id}/delete")
    public String userDelete(@PathVariable Long id) {
        userService.userDelete(id);
        return "redirect:/users";
    }
}
