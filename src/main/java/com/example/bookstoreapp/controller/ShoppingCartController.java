package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.CartItemDto;
import com.example.bookstoreapp.dto.ShoppingCartDto;
import com.example.bookstoreapp.servive.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Shopping cart", description = "Shopping cart")
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get all from shopping cart", description = "Get all from shopping cart")
    @ResponseStatus(HttpStatus.OK)
    ShoppingCartDto getShoppingCart() {
        return shoppingCartService.getShoppingCart();
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Add item to shopping cart", description = "Add item to shopping cart")
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingCartDto addItem(@RequestBody CartItemDto cartItemDto) {
        return shoppingCartService.addItem(cartItemDto);
    }

    @DeleteMapping("/cart-items/{id}")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Delete item from shopping cart",
            description = "Delete item from shopping cart")
    public void deleteCartItem(@PathVariable Long id) {
        shoppingCartService.deleteById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Edit Cart Item", description = "Edit Cart Item")
    public ShoppingCartDto editCartItem(@PathVariable Long id,
                                        @RequestBody CartItemDto cartItemDto) {
        return shoppingCartService.editCartItem(id, cartItemDto);
    }
}
