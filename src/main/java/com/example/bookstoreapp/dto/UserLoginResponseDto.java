package com.example.bookstoreapp.dto;

import lombok.Data;

@Data
public class UserLoginResponseDto {
    public UserLoginResponseDto(String token) {
        this.token = token;
    }

    private String token;
}