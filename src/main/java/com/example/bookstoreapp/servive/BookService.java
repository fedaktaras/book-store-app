package com.example.bookstoreapp.servive;

import com.example.bookstoreapp.dto.BookDto;
import com.example.bookstoreapp.dto.BookRequestDto;
import java.util.List;

public interface BookService {
    BookDto save(BookRequestDto book);

    List<BookDto> findAll();

    BookDto getBookById(Long id);

    BookDto updateBook(BookDto bookDto);

    void deleteById(Long id);
}
