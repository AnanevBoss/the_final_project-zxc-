package com.example.springmodels.services;

import com.example.springmodels.models.Book;
import com.example.springmodels.models.Copy;
import com.example.springmodels.repos.BookRepository;
import com.example.springmodels.repos.CopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CopyService {

    private final CopyRepository copyRepository;
    private final BookRepository bookRepository;

    @Autowired
    public CopyService(CopyRepository copyRepository, BookRepository bookRepository) {
        this.copyRepository = copyRepository;
        this.bookRepository = bookRepository;
    }

    public List<Copy> findAll() {
        return copyRepository.findAll();
    }

    public Copy findById(Long id) {
        return copyRepository.findById(id).orElse(null);
    }

    public Copy saveCopy(Copy copy) {
        return copyRepository.save(copy);
    }
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public void deleteById(Long id) {
        copyRepository.deleteById(id);
    }
}
