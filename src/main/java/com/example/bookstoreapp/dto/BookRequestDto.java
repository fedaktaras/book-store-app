package com.example.bookstoreapp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class BookRequestDto {
    @NotNull
    private String title;
    @NotNull
    @Min(value = 0)
    private BigDecimal price;
    @NotNull
    private String author;
    @NotNull
    @Pattern(regexp = "^(978|979)[-]?\\d{10}$", message = "Invalid ISBN")
    private String isbn;
    @NotNull
    private String description;
    @NotNull
    private String coverImage;
}
