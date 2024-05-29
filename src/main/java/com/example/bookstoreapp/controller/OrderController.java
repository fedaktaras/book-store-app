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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Order", description = "Endpoints for managing orders")
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get all orders", description = "Get all orders of current user")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getAllOrders(@AuthenticationPrincipal User user) {
        return orderService.getAllOrders(user.getId());
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Place an order", description = "Place an order with items that are "
            + "currently in shopping cart")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrder(@RequestBody @Valid PlaceOrderDto placeOrderDto,
                                @AuthenticationPrincipal User user) {
        return orderService.createOrder(placeOrderDto, user.getId());
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update status", description = "Update status of some order. Made by "
            + "Admin only")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto updateStatus(@RequestBody StatusDto statusDto, @PathVariable Long id) {
        return orderService.updateStatus(statusDto, id);
    }

    @GetMapping("{orderId}/items/{itemId}")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get order item", description = "Get order item if that OrderItem "
            + "belongs to current User")
    @ResponseStatus(HttpStatus.OK)
    public OrderItemDto getOrderItem(@PathVariable Long orderId, @PathVariable Long itemId,
                                     @AuthenticationPrincipal User user) {
        return orderService.getOrderItemFromOrder(orderId, itemId, user.getId());
    }

    @GetMapping("{orderId}/items")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get order", description = "Get order by Id if order belongs to current "
            + "User")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto getOrder(@PathVariable Long orderId, @AuthenticationPrincipal User user) {
        return orderService.getOrder(orderId, user.getId());
    }
}
