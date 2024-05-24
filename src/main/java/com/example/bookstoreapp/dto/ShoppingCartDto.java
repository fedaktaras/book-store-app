package com.example.bookstoreapp.dto;

import java.util.Set;
import lombok.Data;

@Data
public class ShoppingCartDto {
    private Long id;
    private Set<CartItemDto> cartItems;
}
