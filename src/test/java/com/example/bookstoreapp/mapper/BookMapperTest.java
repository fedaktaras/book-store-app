package com.example.bookstoreapp.mapper;

import com.example.bookstoreapp.dto.BookDto;
import com.example.bookstoreapp.dto.BookRequestDto;
import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.utils.TestDataUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import lombok.Setter;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookMapperTest {

    @Autowired
    private BookMapper bookMapper;

    @Test
    void toBook() {
        BookRequestDto requestDto = TestDataUtils.getRequestDto("Clean Code");
        Book cleanCode = TestDataUtils.getBook("Clean Code");

        Book book = bookMapper.toBook(requestDto);
        Assertions.assertEquals(cleanCode, book);
    }

    @Test
    void toDtoBook() {
        BookDto cleanCodeDto = TestDataUtils.getDto("Clean Code");
        Book cleanCode = TestDataUtils.getBook("Clean Code");
        BookDto dtoBook = bookMapper.toDtoBook(cleanCode);

        Assertions.assertEquals(dtoBook, cleanCodeDto);
    }
}