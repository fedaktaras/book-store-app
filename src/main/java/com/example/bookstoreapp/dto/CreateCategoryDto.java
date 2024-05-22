package com.example.bookstoreapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateCategoryDto {
    @NotBlank(message = "Name is mandatory")
    private String name;

    private String description;
}
