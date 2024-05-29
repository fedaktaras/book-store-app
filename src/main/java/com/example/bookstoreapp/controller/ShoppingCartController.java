package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.CartItemDto;
import com.example.bookstoreapp.dto.ShoppingCartDto;
import com.example.bookstoreapp.model.User;
import com.example.bookstoreapp.servive.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Shopping cart", description = "Shopping cart management by User")
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get all from shopping cart", description = "Get all from shopping cart "
            + "of current User")
    @ResponseStatus(HttpStatus.OK)
    ShoppingCartDto getShoppingCart(Authentication authentication) {
        return shoppingCartService.getShoppingCart(getId(authentication));
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Add to shopping cart", description = "Add item to shopping cart of "
            + "current User")
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingCartDto addItem(@RequestBody CartItemDto cartItemDto,
                                   Authentication authentication) {
        return shoppingCartService.addItem(cartItemDto, (User) authentication.getPrincipal());
    }

    @DeleteMapping("/cart-items/{id}")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Delete from shopping cart",
            description = "Delete item from current User's shopping cart")
    public void deleteCartItem(@PathVariable Long id) {
        shoppingCartService.deleteById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Edit Cart Item", description = "Edit Cart Item If User is an owner")
    public ShoppingCartDto editCartItem(@PathVariable Long id,
                                        @RequestBody CartItemDto cartItemDto,
                                        Authentication authentication) {
        return shoppingCartService.editCartItem(id, cartItemDto, getId(authentication));
    }

    private Long getId(Authentication authentication) {
        return ((User) authentication.getPrincipal()).getId();
    }
}
