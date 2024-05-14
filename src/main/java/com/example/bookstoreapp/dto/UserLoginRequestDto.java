package com.example.bookstoreapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginRequestDto {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
