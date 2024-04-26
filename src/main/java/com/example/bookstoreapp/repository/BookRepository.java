package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
