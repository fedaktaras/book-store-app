package com.example.bookstoreapp.servive.impl;

import java.util.List;
import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.repository.BookRepository;
import com.example.bookstoreapp.servive.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}