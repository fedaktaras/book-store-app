package com.example.bookstoreapp.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class BookRequestDto {
    private String title;
    private BigDecimal price;
    private String author;
    private String isbn;
    private String description;
    private String coverImage;
}
