package com.example.bookstoreapp.servive.impl;

import com.example.bookstoreapp.dto.BookDto;
import com.example.bookstoreapp.dto.BookRequestDto;
import com.example.bookstoreapp.dto.BookUpdateDto;
import com.example.bookstoreapp.mapper.BookMapper;
import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.repository.BookRepository;
import com.example.bookstoreapp.servive.BookService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import javax.naming.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookMapper bookMapper, BookRepository bookRepository) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDto save(BookRequestDto bookRequestDto) {
        return bookMapper.toDtoBook(bookRepository.save(
                bookMapper.toBook(bookRequestDto)));
    }

    @Override
    public List<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).stream()
                .map(bookMapper::toDtoBook)
                .toList();
    }

    public BookDto getBookById(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toDtoBook)
                .orElseThrow(() ->
                        new EntityNotFoundException("Book not found with id: " + id));
    }

    @Override
    public BookDto updateBook(BookUpdateDto bookUpdateDto, Long id) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Book not found with id: " + id));
        bookMapper.updateBookFromDto(bookUpdateDto, existingBook);
        Book updatedBook = bookRepository.save(existingBook);
        return bookMapper.toDtoBook(updatedBook);
    }

    @Override
    public BookDto updateBook(BookRequestDto bookRequestDto, Long id) {
        bookRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Book not found with id: " + id));
        Book book = bookMapper.toBook(bookRequestDto);
        book.setId(id);
        Book updatedBook = bookRepository.save(book);
        return bookMapper.toDtoBook(updatedBook);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
