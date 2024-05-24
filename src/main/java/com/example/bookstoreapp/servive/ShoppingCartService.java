package com.example.bookstoreapp.servive;

import com.example.bookstoreapp.dto.CartItemDto;
import com.example.bookstoreapp.dto.ShoppingCartDto;
import com.example.bookstoreapp.model.User;

public interface ShoppingCartService {
    ShoppingCartDto addItem(CartItemDto cartItemDto, User user);

    void deleteById(Long id);

    ShoppingCartDto getShoppingCart(Long id);

    ShoppingCartDto editCartItem(Long id, CartItemDto cartItemDto, Long userId);

    void clearShoppingCart();
}
