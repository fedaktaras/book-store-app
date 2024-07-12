package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.BookDto;
import com.example.bookstoreapp.dto.BookRequestDto;
import com.example.bookstoreapp.dto.BookUpdateDto;
import com.example.bookstoreapp.servive.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book management", description = "Endpoints for access and management books")
@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @Operation(summary = "Get all books", description = "Get all books from database")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> getAll(@ParameterObject @PageableDefault(sort = {"price", "title"},
            value = 5) Pageable pageable) {
        return bookService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @Operation(summary = "Get Book by Book Id", description = "Get Book by Book Id")
    @ResponseStatus(HttpStatus.OK)
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a new book", description = "Create a new book by Admin only")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto createBook(@RequestBody BookRequestDto bookDto) {
        return bookService.save(bookDto);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update book by Book Id", description = "Update book by Book Id by Admin"
            + " only. Does not require all fields")
    @ResponseStatus(HttpStatus.OK)
    public BookDto updateBook(@PathVariable Long id, @RequestBody BookUpdateDto bookUpdateDto) {
        return bookService.updateBook(bookUpdateDto, id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update book by Book Id", description = "Update book by Book Id by Admin."
            + "Requires all fields")
    @ResponseStatus(HttpStatus.OK)
    public BookDto updateBook(@PathVariable Long id, @RequestBody BookRequestDto bookRequestDto) {
        return bookService.updateBook(bookRequestDto, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete book by Book Id", description = "Delete book by Book Id by Admin.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        bookService.deleteById(id);
    }
}
