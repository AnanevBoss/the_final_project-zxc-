package com.example.springmodels.controllers;

import com.example.springmodels.models.Author;
import com.example.springmodels.models.Book;
import com.example.springmodels.models.Rayting;
import com.example.springmodels.models.User;
import com.example.springmodels.repos.RaytingRepository;
import com.example.springmodels.services.BookService;
import com.example.springmodels.services.RaytingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RaytingController {

    private final RaytingService raytingService;

    @Autowired
    public RaytingController(RaytingService raytingService) {
        this.raytingService = raytingService;
    }
    @PreAuthorize("hasAnyAuthority('user','admin','employee')")
    @GetMapping("/rayting")
    public String findAll(Model model) {
        List<Rayting> rayting = raytingService.findAll();
        model.addAttribute("rayting", rayting);
        return "rayting-list";
    }
    @PreAuthorize("hasAnyAuthority('admin','employee')")
    @GetMapping("/rayting/create")
    public String createBookForm(Model model) {
        List<User> user = raytingService.findAllAuthors();
        model.addAttribute("user", user);
        model.addAttribute("rayting", new Rayting());
        return "rayting-create";
    }
    @PreAuthorize("hasAnyAuthority('admin','employee')")
    @PostMapping("/rayting/create")
    public String createBook(@ModelAttribute Rayting rayting) {
        raytingService.saveRayting(rayting);
        return "redirect:/rayting";
    }
    @PreAuthorize("hasAnyAuthority('admin','employee')")
    @GetMapping("/rayting/update/{id}")
    public String updateBookForm(@PathVariable Long id, Model model) {
        Rayting rayting = raytingService.findById(id);
        List<User> user = raytingService.findAllAuthors();
        model.addAttribute("rayting", rayting);
        model.addAttribute("user", user);
        return "rayting-update";
    }
    @PreAuthorize("hasAnyAuthority('admin','employee')")
    @PostMapping("/rayting/update")
    public String updateBook(@ModelAttribute Rayting rayting) {
        raytingService.saveRayting(rayting);
        return "redirect:/rayting";
    }
    @PreAuthorize("hasAnyAuthority('admin','employee')")
    @GetMapping("/rayting/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        raytingService.deleteById(id);
        return "redirect:/rayting";
    }
}
