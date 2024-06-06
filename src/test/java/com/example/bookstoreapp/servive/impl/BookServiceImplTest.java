package com.example.bookstoreapp.servive.impl;

import com.example.bookstoreapp.dto.BookDto;
import com.example.bookstoreapp.dto.BookRequestDto;
import com.example.bookstoreapp.mapper.BookMapper;
import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.repository.BookRepository;
import com.example.bookstoreapp.servive.CategoryService;
import com.example.bookstoreapp.utils.TestDataUtils;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceImplTest {
    @InjectMocks
    private BookServiceImpl bookService;
    @Mock
    private BookMapper bookMapper;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private CategoryService categoryService;

    @Test
    void save() {
        BookRequestDto requestDto = TestDataUtils.getRequestDto("Effective Java");
        Book book = TestDataUtils.getBook("Effective Java");
    }

    @Test
    void findAll() {
    }

    @Test
    void getBookById() {
    }

    @Test
    void updateBook() {
    }

    @Test
    void testUpdateBook() {
    }

    @Test
    void findAllByCategoryId() {
    }

    @Test
    void deleteById() {
    }
}