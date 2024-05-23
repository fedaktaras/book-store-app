package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.OrderDto;
import com.example.bookstoreapp.dto.PlaceOrderDto;
import com.example.bookstoreapp.dto.StatusDto;
import com.example.bookstoreapp.servive.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Order", description = "Order")
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get all orders", description = "Get all orders")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Place an order", description = "Place an order")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrder(@RequestBody PlaceOrderDto placeOrderDto) {
        return orderService.createOrder(placeOrderDto);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update status", description = "Update status")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto updateStatus(@RequestBody StatusDto statusDto) {
        return orderService.updateStatus(statusDto);
    }
}
