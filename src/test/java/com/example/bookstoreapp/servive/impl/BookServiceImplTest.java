package com.example.bookstoreapp.servive.impl;

import com.example.bookstoreapp.dto.BookDto;
import com.example.bookstoreapp.dto.BookRequestDto;
import com.example.bookstoreapp.dto.BookUpdateDto;
import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.repository.BookRepository;
import com.example.bookstoreapp.utils.TestDataUtils;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookServiceImplTest {

    @Autowired
    private BookServiceImpl bookService;

    @MockBean
    private BookRepository bookRepository;


    @Test
    void save() {
        BookRequestDto requestDto = TestDataUtils.getRequestDto("Effective Java");
        Book book = TestDataUtils.getBook("Effective Java");
        BookDto bookDto = TestDataUtils.getDto("Effective Java");
        when(bookRepository.save(book)).thenReturn(book);

        BookDto savedBookDto = bookService.save(requestDto);

        assertNotNull(savedBookDto);
        assertEquals(bookDto, savedBookDto);
    }

    @Test
    void findAll() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Book> books = List.of(
                TestDataUtils.getBook("Java Concurrency in Practice"),
                TestDataUtils.getBook("Clean Code"),
                TestDataUtils.getBook("Effective Java")
        );
        List<BookDto> bookDtos = List.of(
                TestDataUtils.getDto("Java Concurrency in Practice"),
                TestDataUtils.getDto("Clean Code"),
                TestDataUtils.getDto("Effective Java")
        );
        Page<Book> bookPage = new PageImpl<>(books, pageable, books.size());
        when(bookRepository.findAll(pageable)).thenReturn(bookPage);

        List<BookDto> result = bookService.findAll(pageable);

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(bookDtos, result);
    }

    @Test
    void getBookById() {
        Book book = TestDataUtils.getBook("Clean Code");
        BookDto bookDto = TestDataUtils.getDto("Clean Code");
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        BookDto result = bookService.getBookById(1L);

        assertNotNull(result);
        assertEquals(bookDto, result);
    }

    @Test
    void updateBook() {
        Book book = TestDataUtils.getBook("Clean Code");
        Book updatedBook = TestDataUtils.getBook("Clean Code");
        updatedBook.setPrice(BigDecimal.valueOf(80));

        BookUpdateDto bookUpdateDto = new BookUpdateDto();
        bookUpdateDto.setCategoryIds(new HashSet<>());
        bookUpdateDto.setPrice(BigDecimal.valueOf(80));

        when(bookRepository.findById(any(Long.class))).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenReturn(updatedBook);

        BookDto updatedBookDto = bookService.updateBook(bookUpdateDto, 1L);

        assertNotNull(updatedBookDto);
        assertEquals(BigDecimal.valueOf(80), updatedBookDto.getPrice());
    }

    @Test
    void findAllByCategoryId() {
    }

    @Test
    void deleteById() {
    }
}