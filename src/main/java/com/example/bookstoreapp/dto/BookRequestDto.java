package com.example.bookstoreapp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;
import lombok.Data;
import org.hibernate.validator.constraints.ISBN;

@Data
public class BookRequestDto {
    @NotBlank(message = "Title cannot be blank")
    @NotNull
    private String title;
    @NotNull
    @Min(value = 0)
    private BigDecimal price;
    @NotBlank
    @NotNull
    private String author;
    @NotNull
    @ISBN
    private String isbn;
    @NotNull
    private String description;
    @NotNull
    private String coverImage;
    @NotNull
    private Set<Long> categoryIds;
}
