package com.example.bookstoreapp.dto;

import lombok.Data;

@Data
public class OrderItemDto {
    private Long id;
    private Long bookId;
    private int quantity;

    public OrderItemDto(CartItemDto cartItemDto) {
        this.bookId = cartItemDto.getBookId();
        this.quantity = cartItemDto.getQuantity();
    }

    public OrderItemDto() {
    }
}
