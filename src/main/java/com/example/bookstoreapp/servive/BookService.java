package com.example.bookstoreapp.servive;

import com.example.bookstoreapp.dto.BookDto;
import com.example.bookstoreapp.dto.BookRequestDto;
import com.example.bookstoreapp.dto.BookUpdateDto;
import java.util.List;

public interface BookService {
    BookDto save(BookRequestDto book);

    List<BookDto> findAll();

    BookDto getBookById(Long id);

    BookDto updateBook(BookUpdateDto bookUpdateDto, Long id);

    BookDto updateBook(BookRequestDto bookRequestDto, Long id);

    void deleteById(Long id);
}
