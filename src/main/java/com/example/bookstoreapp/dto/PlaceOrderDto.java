package com.example.bookstoreapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PlaceOrderDto {
    @NotBlank(message = "Shipping address cannot be empty")
    private String shippingAddress;
}
