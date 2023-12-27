package com.example.springmodels.controllers;

import com.example.springmodels.models.Copy;
import com.example.springmodels.models.Loan;
import com.example.springmodels.models.User;
import com.example.springmodels.services.CopyService;
import com.example.springmodels.services.LoanService;
import com.example.springmodels.services.UserService;
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
public class LoanController {

    private final LoanService loanService;
    private final CopyService copyService;
    private final UserService userService;

    @Autowired
    public LoanController(LoanService loanService, CopyService copyService, UserService userService) {
        this.loanService = loanService;
        this.copyService = copyService;
        this.userService = userService;
    }
    @PreAuthorize("hasAnyAuthority('admin','employee')")
    @GetMapping("/loans")
    public String findAll(Model model) {
        List<Loan> loans = loanService.findAll();
        model.addAttribute("loans", loans);
        return "loan-list";
    }

    @PreAuthorize("hasAnyAuthority('admin','employee')")
    @GetMapping("/loans/create")
    public String createLoanForm(Loan loan, Model model) {
        List<Copy> copies = copyService.findAll();
        List<User> users = userService.findAll();
        model.addAttribute("copies", copies);
        model.addAttribute("users", users);
        return "loan-create";
    }

    @PreAuthorize("hasAnyAuthority('admin','employee')")
    @PostMapping("/loans/create")
    public String createLoan(Loan loan) {
        loanService.saveLoan(loan);
        return "redirect:/loans";
    }
    @PreAuthorize("hasAnyAuthority('admin','employee')")
    @GetMapping("/loans/update/{id}")
    public String updateLoanForm(@PathVariable Long id, Model model) {
        Loan loan = loanService.findById(id);
        List<Copy> copies = copyService.findAll();
        List<User> users = userService.findAll();
        model.addAttribute("loan", loan);
        model.addAttribute("copies", copies);
        model.addAttribute("users", users);
        return "loan-update";
    }
    @PreAuthorize("hasAnyAuthority('admin','employee')")
    @PostMapping("/loans/update/")
    public String updateLoan(@ModelAttribute("loan") Loan loan) {
        loanService.saveLoan(loan);
        return "redirect:/loans";
    }


    @GetMapping("/loans/delete/{id}")
    public String deleteLoan(@PathVariable Long id) {
        loanService.deleteById(id);
        return "redirect:/loans";
    }
}
