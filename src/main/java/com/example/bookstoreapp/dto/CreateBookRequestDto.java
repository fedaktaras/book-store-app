package com.example.bookstoreapp.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBookRequestDto {
    private String title;
    private BigDecimal price;
    private String author;
    private String isbn;
    private String description;
    private String coverImage;
}
