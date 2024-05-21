package com.example.bookstoreapp.servive;

import com.example.bookstoreapp.dto.CartItemDto;
import com.example.bookstoreapp.dto.ShoppingCartDto;

public interface ShoppingCartService {
    ShoppingCartDto addItem(CartItemDto cartItemDto);

    void deleteById(Long id);

    ShoppingCartDto getShoppingCart();

    ShoppingCartDto editCartItem(Long id, CartItemDto cartItemDto);
}
