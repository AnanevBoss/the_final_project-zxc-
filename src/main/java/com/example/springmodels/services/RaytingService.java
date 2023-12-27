package com.example.springmodels.services;

import com.example.springmodels.models.Author;
import com.example.springmodels.models.Book;
import com.example.springmodels.models.Rayting;
import com.example.springmodels.models.User;
import com.example.springmodels.repos.BookRepository;
import com.example.springmodels.repos.RaytingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaytingService {

    private final RaytingRepository raytingRepository;
    private final UserService userService;

    @Autowired
    public RaytingService(RaytingRepository raytingRepository, UserService userService) {
        this.raytingRepository = raytingRepository;
        this.userService = userService;
    }

    public List<Rayting> findAll() {
        return raytingRepository.findAll();
    }

    public Rayting findById(Long id) {
        return raytingRepository.findById(id).orElse(null);
    }

    public Rayting saveRayting(Rayting rayting) {
        return raytingRepository.save(rayting);
    }

    public void deleteById(Long id) {
        raytingRepository.deleteById(id);
    }


    public List<User> findAllAuthors() {
        return userService.findAll();
    }
}
