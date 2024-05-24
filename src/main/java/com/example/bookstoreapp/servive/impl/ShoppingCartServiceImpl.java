package com.example.bookstoreapp.servive.impl;

import com.example.bookstoreapp.dto.CartItemDto;
import com.example.bookstoreapp.dto.ShoppingCartDto;
import com.example.bookstoreapp.mapper.CartItemMapper;
import com.example.bookstoreapp.mapper.ShoppingCartMapper;
import com.example.bookstoreapp.model.CartItem;
import com.example.bookstoreapp.model.ShoppingCart;
import com.example.bookstoreapp.model.User;
import com.example.bookstoreapp.repository.CartItemRepository;
import com.example.bookstoreapp.repository.ShoppingCartRepository;
import com.example.bookstoreapp.repository.UserRepository;
import com.example.bookstoreapp.servive.ShoppingCartService;
import com.example.bookstoreapp.servive.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private CartItemMapper cartItemMapper;
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    private final CartItemRepository cartItemRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ShoppingCartDto addItem(CartItemDto cartItemDto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user;
        if (principal instanceof User) {
            user = (User) principal;
        } else {
            throw new RuntimeException("Smth gone wrong");
        }

        ShoppingCart shoppingCart = shoppingCartRepository.findById(user.getId()).orElseThrow();
        CartItem newCartItem = cartItemMapper.toEntity(cartItemDto);
        newCartItem.setShoppingCart(shoppingCart);

        boolean itemExists = false;
        for (CartItem item : shoppingCart.getCartItems()) {
            if (item.getBook().getId().equals(newCartItem.getBook().getId())) {
                item.setQuantity(newCartItem.getQuantity() + item.getQuantity());
                itemExists = true;
                break;
            }
        }
        if (!itemExists) {
            shoppingCart.getCartItems().add(newCartItem);
        }

        user.setShoppingCart(shoppingCart);
        shoppingCart.setUser(user);
        userRepository.save(user);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public void deleteById(Long id) {
        ShoppingCart shoppingCart = getUsersShoppingCart();
        CartItem cartItem = getCartItemFromUsersShoppingCart(id);
        shoppingCart.getCartItems().remove(cartItem);
        shoppingCartRepository.save(shoppingCart);
        cartItemRepository.deleteById(id);
    }

    @Override
    public ShoppingCartDto editCartItem(Long id, CartItemDto cartItemDto) {
        ShoppingCart usersShoppingCart = getUsersShoppingCart();
        CartItem cartItem = cartItemMapper.toEntity(cartItemDto);
        cartItem.setShoppingCart(usersShoppingCart);
        cartItemRepository.save(cartItem);
        return shoppingCartMapper.toDto(shoppingCartRepository
                .findById(userService.getCurrentUserId()).get());
    }

    @Override
    public ShoppingCartDto getShoppingCart() {
        ShoppingCart shoppingCart = shoppingCartRepository
                .findById(userService.getCurrentUserId()).orElseThrow();
        return shoppingCartMapper.toDto(shoppingCart);
    }

    private CartItem getCartItemFromUsersShoppingCart(Long id) {
        ShoppingCart shoppingCart = getUsersShoppingCart();
        return shoppingCart.getCartItems().stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new AccessDeniedException("You do not have permission to "
                        + "delete this item"));
    }

    private ShoppingCart getUsersShoppingCart() {
        Long userId = userService.getCurrentUserId();
        return shoppingCartRepository.findById(userId).get();
    }
}
