package com.example.springmodels.controllers;

import com.example.springmodels.models.Copy;
import com.example.springmodels.services.CopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CopyController {

    private final CopyService copyService;

    @Autowired
    public CopyController(CopyService copyService) {
        this.copyService = copyService;
    }
    @PreAuthorize("hasAnyAuthority('user','admin','employee')")
    @GetMapping("/copies")
    public String findAll(Model model) {
        List<Copy> copies = copyService.findAll();
        model.addAttribute("copies", copies);
        return "copy-list";
    }
    @PreAuthorize("hasAnyAuthority('admin','employee')")
    @GetMapping("/copies/create")
    public String createCopyForm(Copy copy, Model model) {
        model.addAttribute("books", copyService.findAllBooks());
        return "copy-create";
    }
    @PreAuthorize("hasAnyAuthority('admin','employee')")
    @PostMapping("/copies/create")
    public String createCopy(Copy copy) {
        copyService.saveCopy(copy);
        return "redirect:/copies";
    }
    @PreAuthorize("hasAnyAuthority('admin','employee')")
    @GetMapping("/copies/update/{id}")
    public String updateCopyForm(@PathVariable Long id, Model model) {
        Copy copy = copyService.findById(id);
        model.addAttribute("copy", copy);
        model.addAttribute("books", copyService.findAllBooks());
        return "copy-update";
    }
    @PreAuthorize("hasAnyAuthority('admin','employee')")
    @PostMapping("/copies/update")
    public String updateCopy(Copy copy) {
        copyService.saveCopy(copy);
        return "redirect:/copies";
    }
    @PreAuthorize("hasAnyAuthority('admin','employee')")
    @GetMapping("/copies/delete/{id}")
    public String deleteCopy(@PathVariable Long id) {
        copyService.deleteById(id);
        return "redirect:/copies";
    }
}
