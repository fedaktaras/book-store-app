package com.example.bookstoreapp.servive;

import java.util.List;
import com.example.bookstoreapp.model.Book;

public interface BookService {
    Book save(Book book);
    List<Book> findAll();
}
