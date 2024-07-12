package com.example.bookstoreapp.mapper;

import com.example.bookstoreapp.dto.BookDto;
import com.example.bookstoreapp.dto.BookRequestDto;
import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.utils.TestDataUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookMapperTest {

    @Autowired
    private BookMapper bookMapper;

    private TestDataUtils testDataUtils;

    @BeforeEach
    public void newTestDataUtils() {
        testDataUtils = new TestDataUtils();
    }
    @Test
    void toBook() {
        BookRequestDto requestDto = testDataUtils.getRequestDto("Clean Code");
        Book cleanCode = testDataUtils.getBook("Clean Code");

        Book book = bookMapper.toBook(requestDto);
        Assertions.assertEquals(cleanCode, book);
    }

    @Test
    void toDtoBook() {
        BookDto cleanCodeDto = testDataUtils.getDto("Clean Code");
        Book cleanCode = testDataUtils.getBook("Clean Code");
        BookDto dtoBook = bookMapper.toDtoBook(cleanCode);

        Assertions.assertEquals(dtoBook, cleanCodeDto);
    }
}