package com.example.springmodels.controllers;

import com.example.springmodels.models.Book;
import com.example.springmodels.models.Review;
import com.example.springmodels.models.User;
import com.example.springmodels.services.BookService;
import com.example.springmodels.services.ReviewService;
import com.example.springmodels.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final BookService bookService;
    private final UserService userService;

    @Autowired
    public ReviewController(ReviewService reviewService, BookService bookService, UserService userService) {
        this.reviewService = reviewService;
        this.bookService = bookService;
        this.userService = userService;
    }
    @PreAuthorize("hasAnyAuthority('user','admin','employee')")
    @GetMapping
    public String listReviews(Model model) {
        List<Review> reviews = reviewService.findAll();
        model.addAttribute("reviews", reviews);
        return "review-list";
    }
    @PreAuthorize("hasAnyAuthority('user','admin','employee')")
    @GetMapping("/create")
    public String createReviewForm(Model model) {
        List<Book> books = bookService.findAll();
        List<User> users = userService.findAll();

        model.addAttribute("books", books);
        model.addAttribute("users", users);
        model.addAttribute("review", new Review());

        return "review-create";
    }
    @PreAuthorize("hasAnyAuthority('user','admin','employee')")
    @PostMapping("/create")
    public String createReview(@ModelAttribute("review") Review review) {
        reviewService.saveReview(review);
        return "redirect:/reviews";
    }
    @PreAuthorize("hasAnyAuthority('admin','employee')")
    @GetMapping("/update/{id}")
    public String updateReviewForm(@PathVariable Long id, Model model) {
        Review review = reviewService.findById(id);
        List<Book> books = bookService.findAll();
        List<User> users = userService.findAll();

        model.addAttribute("review", review);
        model.addAttribute("books", books);
        model.addAttribute("users", users);

        return "review-update";
    }
    @PreAuthorize("hasAnyAuthority('admin','employee')")
    @PostMapping("/update")
    public String updateReview(@ModelAttribute("review") Review review) {
        reviewService.saveReview(review);
        return "redirect:/reviews";
    }
    @PreAuthorize("hasAnyAuthority('admin','employee')")
    @GetMapping("/delete/{id}")
    public String deleteReview(@PathVariable Long id) {
        reviewService.deleteById(id);
        return "redirect:/reviews";
    }
}
