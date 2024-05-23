package com.example.bookstoreapp.servive;

import com.example.bookstoreapp.dto.UserRegistrationRequestDto;
import com.example.bookstoreapp.dto.UserResponseDto;
import com.example.bookstoreapp.exception.RegistrationException;
import com.example.bookstoreapp.model.ShoppingCart;
import com.example.bookstoreapp.model.User;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto userRegistrationRequestDto)
            throws RegistrationException;

    Long getCurrentUserId();

    User getCurrentUser();

    ShoppingCart getCurrentUserShoppingCart();
}
