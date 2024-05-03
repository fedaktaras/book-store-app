package com.example.bookstoreapp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import lombok.Data;
import org.hibernate.validator.constraints.ISBN;

@Data
public class BookUpdateDto {
    @NotBlank(message = "Title cannot be blank")
    private String title;
    @Min(value = 0)
    private BigDecimal price;
    private String author;
    @ISBN
    private String isbn;
    private String description;
    private String coverImage;
}
