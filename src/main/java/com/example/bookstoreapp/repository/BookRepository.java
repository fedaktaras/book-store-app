package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.model.Book;
import java.util.List;

public interface BookRepository {
    Book save(Book book);

    void delete(Book book);

    Book findById(Long id);

    List<Book> findAll();
}
