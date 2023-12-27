package com.example.springmodels.services;

import com.example.springmodels.models.Copy;
import com.example.springmodels.models.Loan;
import com.example.springmodels.models.User;
import com.example.springmodels.repos.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final CopyService copyService;
    private final UserService userService;

    @Autowired
    public LoanService(LoanRepository loanRepository, CopyService copyService, UserService userService) {
        this.loanRepository = loanRepository;
        this.copyService = copyService;
        this.userService = userService;
    }

    public List<Loan> findAll() {
        return loanRepository.findAll();
    }

    public Loan findById(Long id) {
        return loanRepository.findById(id).orElse(null);
    }

    public Loan saveLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    public void deleteById(Long id) {
        loanRepository.deleteById(id);
    }

    public List<Copy> findAllCopies() {
        return copyService.findAll();
    }

    public List<User> findAllUsers() {
        return userService.findAll();
    }
}
