package com.example.bookstoreapp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemDto {
    private Long id;
    @NotNull(message = "Book ID is required")
    private Long bookId;
    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    public OrderItemDto(CartItemDto cartItemDto) {
        this.bookId = cartItemDto.getBookId();
        this.quantity = cartItemDto.getQuantity();
    }
}
