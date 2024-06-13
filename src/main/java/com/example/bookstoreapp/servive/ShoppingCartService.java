package com.example.bookstoreapp.servive;

import com.example.bookstoreapp.dto.CartItemDto;
import com.example.bookstoreapp.dto.CartItemRequestDto;
import com.example.bookstoreapp.dto.ShoppingCartDto;
import com.example.bookstoreapp.model.ShoppingCart;
import com.example.bookstoreapp.model.User;

public interface ShoppingCartService {
    ShoppingCartDto addItem(CartItemRequestDto cartItemRequestDto, User user);

    void deleteById(Long id, Long userId);

    ShoppingCartDto getShoppingCart(Long id);

    ShoppingCartDto editCartItem(Long id, CartItemDto cartItemDto, Long userId);

    ShoppingCart getCurrentUserShoppingCart();

    void clearShoppingCart(Long userId);
}
