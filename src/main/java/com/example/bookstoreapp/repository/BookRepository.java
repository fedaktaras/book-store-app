package com.example.bookstoreapp.repository;

import java.util.List;
import com.example.bookstoreapp.model.Book;
import org.springframework.stereotype.Repository;


public interface BookRepository {
    Book save(Book book);
    void delete(Book book);
    Book findById(Long id);
    List<Book> findAll();

}