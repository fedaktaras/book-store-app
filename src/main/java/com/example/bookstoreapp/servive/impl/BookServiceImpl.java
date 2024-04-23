package com.example.bookstoreapp.servive.impl;

import com.example.bookstoreapp.dto.BookDto;
import com.example.bookstoreapp.dto.CreateBookRequestDto;
import com.example.bookstoreapp.mapper.BookMapper;
import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.repository.BookRepository;
import com.example.bookstoreapp.servive.BookService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    private BookMapper bookMapper;
    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookDto save(CreateBookRequestDto createBookRequestDto) {
        return bookMapper.bookToBookDto(bookRepository.save(
                bookMapper.createBookRequestDtoToBook(createBookRequestDto)));
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(book -> bookMapper.bookToBookDto(book))
                .toList();
    }

    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
        return bookMapper.bookToBookDto(book);
    }
}
