package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.OrderDto;
import com.example.bookstoreapp.dto.OrderItemDto;
import com.example.bookstoreapp.dto.PlaceOrderDto;
import com.example.bookstoreapp.dto.StatusDto;
import com.example.bookstoreapp.model.User;
import com.example.bookstoreapp.servive.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Order", description = "Order")
@RestController
@RequestMapping("/orders")
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
    public OrderDto createOrder(@RequestBody @Valid PlaceOrderDto placeOrderDto,
                                Authentication authentication) {
        return orderService.createOrder(placeOrderDto, getUserId(authentication));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update status", description = "Update status")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto updateStatus(@RequestBody StatusDto statusDto, @PathVariable Long id) {
        return orderService.updateStatus(statusDto, id);
    }

    @GetMapping("{orderId}/items/{itemId}")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get order item", description = "Get order item")
    @ResponseStatus(HttpStatus.OK)
    public OrderItemDto getOrderItem(@PathVariable Long orderId, @PathVariable Long itemId,
                                     Authentication authentication) {
        return orderService.getOrderItemFromOrder(orderId, itemId, getUserId(authentication));
    }

    @GetMapping("{orderId}/items")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get order item", description = "Get order item")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto getOrder(@PathVariable Long orderId) {
        return orderService.getOrder(orderId);
    }

    private Long getUserId(Authentication authentication) {
        return  ((User) authentication.getPrincipal()).getId();
    }
}
