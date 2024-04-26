package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.BookDto;
import com.example.bookstoreapp.dto.CreateBookRequestDto;
import com.example.bookstoreapp.servive.BookService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDto>> getAll() {
        List<BookDto> books = bookService.findAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        BookDto bookDto = bookService.getBookById(id);
        return ResponseEntity.ok(bookDto);
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody CreateBookRequestDto bookDto) {
        BookDto createdBook = bookService.save(bookDto);
        return ResponseEntity.ok(createdBook);
    }
}
