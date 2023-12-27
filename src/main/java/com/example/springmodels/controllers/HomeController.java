package com.example.springmodels.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/home/authors")
    public String authors() {
        return "author-list";
    }

    @GetMapping("/home/genres")
    public String genres() {
        return "genres";
    }

    @GetMapping("/home/books")
    public String books() {
        return "books";
    }

    @GetMapping("/home/copies")
    public String copies() {
        return "copies";
    }

    @GetMapping("/home/loans")
    public String loans() {
        return "loans";
    }

    @GetMapping("/home/reviews")
    public String reviews() {
        return "reviews";
    }
    @GetMapping("/home/users")
    public String users() {
        return "users";
    }

}
