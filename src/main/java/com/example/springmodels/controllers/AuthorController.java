package com.example.springmodels.controllers;

import com.example.springmodels.models.Author;
import com.example.springmodels.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }



    @PreAuthorize("hasAnyAuthority('user','admin','employee')")
    @GetMapping("/authors")
    public String findAll(Model model) {
        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        return "author-list";
    }
    @PreAuthorize("hasAnyAuthority('admin','employee')")
    @GetMapping("/authors/create")
    public String createAuthorForm(Author author) {
        return "author-create";
    }

    @PostMapping("/authors/create")
    @PreAuthorize("hasAnyAuthority('admin','employee')")
    public String createAuthor(Author author) {
        authorService.saveAuthor(author);
        return "redirect:/authors";
    }

    @GetMapping("/authors/update/{id}")
    @PreAuthorize("hasAnyAuthority('admin','employee')")
    public String updateAithorForm(@PathVariable Long id, Model model) {
        Author author = authorService.findbyId(id);
        model.addAttribute("author", author);
        return "author-update";
    }

    @PostMapping("/authors/update")
    @PreAuthorize("hasAnyAuthority('admin','employee')")
    public String updateAuthor(Author author) {
        authorService.saveAuthor(author);
        return "redirect:/authors";
    }

    @GetMapping("/authors/delete/{id}")
    @PreAuthorize("hasAnyAuthority('admin','employee')")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.deleteById(id);
        return "redirect:/authors";
    }
}
