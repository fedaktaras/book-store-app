package com.example.bookstoreapp.dto;

import java.math.BigDecimal;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class BookDto {
    @EqualsAndHashCode.Exclude
    private Long id;
    private String title;
    private BigDecimal price;
    private String author;
    private String isbn;
    private String description;
    private String coverImage;
    private Set<CategoryDto> categories;
}
