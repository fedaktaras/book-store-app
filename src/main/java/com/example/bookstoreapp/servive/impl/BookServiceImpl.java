package com.example.bookstoreapp.servive.impl;

import com.example.bookstoreapp.dto.BookDto;
import com.example.bookstoreapp.dto.BookRequestDto;
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
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookDto save(BookRequestDto bookRequestDto) {
        return bookMapper.bookToBookDto(bookRepository.save(
                bookMapper.createBookRequestDtoToBook(bookRequestDto)));
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::bookToBookDto)
                .toList();
    }

    public BookDto getBookById(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::bookToBookDto)
                .orElseThrow(() ->
                        new EntityNotFoundException("Book not found with id: " + id));
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        Book existingBook = bookRepository.findById(bookDto.getId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Book not found with id: " + bookDto.getId()));
        bookMapper.updateBookFromDto(bookDto, existingBook);
        Book updatedBook = bookRepository.save(existingBook);
        return bookMapper.bookToBookDto(updatedBook);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
