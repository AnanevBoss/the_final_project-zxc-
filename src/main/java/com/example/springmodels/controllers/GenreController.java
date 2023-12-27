package com.example.springmodels.controllers;

import com.example.springmodels.models.Genre;
import com.example.springmodels.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }
    @PreAuthorize("hasAnyAuthority('user','admin','employee')")
    @GetMapping
    public String findAll(Model model) {
        List<Genre> genres = genreService.findAll();
        model.addAttribute("genres", genres);
        return "genre-list";
    }
    @PreAuthorize("hasAnyAuthority('admin','employee')")
    @GetMapping("/create")
    public String createGenreForm(Genre genre) {
        return "genre-create";
    }
    @PreAuthorize("hasAnyAuthority('admin','employee')")
    @PostMapping("/create")
    public String createGenre(Genre genre) {
        genreService.saveGenre(genre);
        return "redirect:/genres";
    }
    @PreAuthorize("hasAnyAuthority('admin','employee')")
    @GetMapping("/update/{id}")
    public String updateGenreForm(@PathVariable Long id, Model model) {
        Genre genre = genreService.findById(id);
        model.addAttribute("genre", genre);
        return "genre-update";
    }
    @PreAuthorize("hasAnyAuthority('admin','employee')")
    @PostMapping("/update")
    public String updateGenre(Genre genre) {
        genreService.saveGenre(genre);
        return "redirect:/genres";
    }
    @PreAuthorize("hasAnyAuthority('admin','employee')")
    @GetMapping("/delete/{id}")
    public String deleteGenre(@PathVariable Long id) {
        genreService.deleteById(id);
        return "redirect:/genres";
    }
}
