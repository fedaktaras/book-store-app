package com.example.bookstoreapp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class BookUpdateDto {
    private String title;
    @Min(value = 0)
    private BigDecimal price;
    private String author;
    @Pattern(regexp = "^(978|979)[-]?\\d{10}$", message = "Invalid ISBN")
    private String isbn;
    private String description;
    private String coverImage;
}
