package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.UserLoginRequestDto;
import com.example.bookstoreapp.dto.UserLoginResponseDto;
import com.example.bookstoreapp.dto.UserRegistrationRequestDto;
import com.example.bookstoreapp.dto.UserResponseDto;
import com.example.bookstoreapp.exception.RegistrationException;
import com.example.bookstoreapp.security.AuthenticationService;
import com.example.bookstoreapp.servive.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book management", description = "Endpoints for registration and login")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/registration")
    public UserResponseDto register(@RequestBody @Valid UserRegistrationRequestDto request)
            throws RegistrationException {
        return userService.register(request);
    }

    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto requestDto) {
        return authenticationService.login(requestDto);
    }
}
