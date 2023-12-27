package com.example.springmodels.controllers;

import com.example.springmodels.models.User;
import com.example.springmodels.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }
    @PreAuthorize("hasAnyAuthority('admin')")
    @GetMapping("/create")
    public String createUserForm(User user) {
        return "user-create";
    }
    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping("/create")
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }
    @PreAuthorize("hasAnyAuthority('admin')")
    @GetMapping("/update/{id}")
    public String updateUserForm(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user-create";
    }
    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping("/update")
    public String updateUser(User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }
    @PreAuthorize("hasAnyAuthority('admin')")
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/users";
    }
}
