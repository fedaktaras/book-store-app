package com.example.bookstoreapp.dto;

import com.example.bookstoreapp.validator.FieldMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@FieldMatch(firstFieldName = "password", secondFieldName = "passwordRepeated")
public class UserRegistrationRequestDto {
    @NotBlank
    @Email
    @Size(max = 255)
    private String email;
    @NotBlank
    @Size(min = 8, max = 50, message = "Password must be between 8 and 50 characters long")
    private String password;
    @NotBlank
    private String passwordRepeated;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String shippingAddress;
}
