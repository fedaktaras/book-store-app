package com.example.bookstoreapp.utils;

import com.example.bookstoreapp.dto.BookDto;
import com.example.bookstoreapp.dto.BookRequestDto;
import com.example.bookstoreapp.dto.CategoryDto;
import com.example.bookstoreapp.model.Book;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Set;
import com.example.bookstoreapp.model.Category;
import java.util.stream.Collectors;

public class TestDataUtils {
    private final HashMap<String, BookRequestDto> testRequestDto = new HashMap<>();
    private final HashMap<String, BookDto> testDto = new HashMap<>();
    private final HashMap<String, Book> testBooks = new HashMap<>();

    public TestDataUtils() {
        addTestData(
                "Java Concurrency in Practice", new BigDecimal("55.00"),
                "Brian Goetz", "978-0321349606",
                "Comprehensive guide to concurrency in Java", "image3.jpg", Set.of(1L)
        );
        addTestData(
                "Clean Code", new BigDecimal("50.00"), "Robert C. Martin",
                "978-0132350884", "A Handbook of Agile Software Craftsmanship",
                "image2.jpg", Set.of(1L)
        );
        addTestData(
                "Effective Java", new BigDecimal("45.00"), "Joshua Bloch",
                "978-0134685991", "Best practices for Java programming",
                "https://example.com/effective-java.jpg", Set.of(1L)
        );
    }

    private void addTestData(
            String title, BigDecimal price, String author, String isbn,
            String description, String coverImage, Set<Long> categoryIds
    ) {
        BookRequestDto dto = createTestBookRequestDto(
                title, price, author, isbn, description, coverImage, categoryIds
        );
        testRequestDto.put(title, dto);

        Book book = createTestBook(
                title, price, author, isbn, description, coverImage, categoryIds
        );
        testBooks.put(title, book);

        BookDto bookDto = createTestBookDto(
                null, title, price, author, isbn, description, coverImage, categoryIds
        );
        testDto.put(title, bookDto);
    }

    public BookRequestDto getRequestDto(String title) {
        return testRequestDto.get(title);
    }

    public BookDto getDto(String title) {
        return testDto.get(title);
    }

    public Book getBook(String title) {
        return testBooks.get(title);
    }

    private BookRequestDto createTestBookRequestDto(
            String title, BigDecimal price, String author, String isbn,
            String description, String coverImage, Set<Long> categoryIds
    ) {
        BookRequestDto dto = new BookRequestDto();
        dto.setTitle(title);
        dto.setPrice(price);
        dto.setAuthor(author);
        dto.setIsbn(isbn);
        dto.setDescription(description);
        dto.setCoverImage(coverImage);
        dto.setCategoryIds(categoryIds);
        return dto;
    }

    private Book createTestBook(
            String title, BigDecimal price, String author, String isbn,
            String description, String coverImage, Set<Long> categoryIds
    ) {
        Book book = new Book();
        book.setTitle(title);
        book.setPrice(price);
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setDescription(description);
        book.setCoverImage(coverImage);
        Set<Category> categories = categoryIds.stream().map(id -> {
            Category category = new Category();
            category.setId(id);
            return category;
        }).collect(Collectors.toSet());
        book.setCategories(categories);
        return book;
    }

    private BookDto createTestBookDto(
            Long id, String title, BigDecimal price, String author, String isbn,
            String description, String coverImage, Set<Long> categoryIds
    ) {
        BookDto dto = new BookDto();
        dto.setId(id);
        dto.setTitle(title);
        dto.setPrice(price);
        dto.setAuthor(author);
        dto.setIsbn(isbn);
        dto.setDescription(description);
        dto.setCoverImage(coverImage);
        Set<CategoryDto> categories = categoryIds.stream().map(categoryId -> {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(categoryId);
            return categoryDto;
        }).collect(Collectors.toSet());
        dto.setCategories(categories);
        return dto;
    }
}
