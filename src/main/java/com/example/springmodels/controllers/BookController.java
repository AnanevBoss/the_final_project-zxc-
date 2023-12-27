package com.example.springmodels.controllers;

import com.example.springmodels.models.Author;
import com.example.springmodels.models.Book;
import com.example.springmodels.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @PreAuthorize("hasAnyAuthority('user','admin','employee')")
    @GetMapping("/books")
    public String findAll(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "book-list";
    }
    @PreAuthorize("hasAnyAuthority('admin','employee')")
    @GetMapping("/books/create")
    public String createBookForm(Model model) {
        List<Author> authors = bookService.findAllAuthors();
        model.addAttribute("authors", authors);
        model.addAttribute("book", new Book());
        return "book-create";
    }
    @PreAuthorize("hasAnyAuthority('admin','employee')")
    @PostMapping("/books/create")
    public String createBook(@ModelAttribute Book book) {
        bookService.saveBook(book);
        return "redirect:/books";
    }
    @PreAuthorize("hasAnyAuthority('admin','employee')")
    @GetMapping("/books/update/{id}")
    public String updateBookForm(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id);
        List<Author> authors = bookService.findAllAuthors();
        model.addAttribute("book", book);
        model.addAttribute("authors", authors);
        return "book-update";
    }
    @PreAuthorize("hasAnyAuthority('admin','employee')")
    @PostMapping("/books/update")
    public String updateBook(@ModelAttribute Book book) {
        bookService.saveBook(book);
        return "redirect:/books";
    }
    @PreAuthorize("hasAnyAuthority('admin','employee')")
    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }
}
