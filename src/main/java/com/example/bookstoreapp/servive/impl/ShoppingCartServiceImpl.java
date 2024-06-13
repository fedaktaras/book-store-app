package com.example.bookstoreapp.servive.impl;

import com.example.bookstoreapp.dto.CartItemDto;
import com.example.bookstoreapp.dto.CartItemRequestDto;
import com.example.bookstoreapp.dto.ShoppingCartDto;
import com.example.bookstoreapp.mapper.CartItemMapper;
import com.example.bookstoreapp.mapper.ShoppingCartMapper;
import com.example.bookstoreapp.model.CartItem;
import com.example.bookstoreapp.model.ShoppingCart;
import com.example.bookstoreapp.model.User;
import com.example.bookstoreapp.repository.CartItemRepository;
import com.example.bookstoreapp.repository.ShoppingCartRepository;
import com.example.bookstoreapp.servive.ShoppingCartService;
import com.example.bookstoreapp.servive.UserService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.HashSet;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final CartItemMapper cartItemMapper;
    private final ShoppingCartMapper shoppingCartMapper;
    private final CartItemRepository cartItemRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserService userService;
    private final EntityManager entityManager;

    @Override
    @Transactional
    public ShoppingCartDto addItem(CartItemRequestDto cartItemRequestDto, User user) {
        Long userId = user.getId();
        ShoppingCart shoppingCart = shoppingCartRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("Can't find Shopping cart by id: "
                + userId));
        CartItem newCartItem = cartItemMapper.toEntity(cartItemRequestDto);
        newCartItem.setShoppingCart(shoppingCart);
        shoppingCart.setUser(user);

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
        if (!entityManager.contains(user)) {
            user = entityManager.merge(user);
        }
        shoppingCart.setUser(user);

        shoppingCartRepository.save(shoppingCart);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public void deleteById(Long id, Long userId) {
        ShoppingCart shoppingCart = getUsersShoppingCartById(userId);
        CartItem cartItem = getCartItemFromUsersShoppingCart(id,userId);
        shoppingCart.getCartItems().remove(cartItem);
        shoppingCartRepository.save(shoppingCart);
        cartItemRepository.deleteById(id);
    }

    @Override
    public ShoppingCartDto editCartItem(Long id, CartItemDto cartItemDto, Long userId) {
        ShoppingCart usersShoppingCart = getUsersShoppingCartById(userId);
        CartItem cartItem = cartItemMapper.toEntity(cartItemDto);
        cartItem.setShoppingCart(usersShoppingCart);
        cartItemRepository.save(cartItem);
        return shoppingCartMapper.toDto(shoppingCartRepository
                .findById(userService.getCurrentUserId()).get());
    }

    @Override
    public ShoppingCartDto getShoppingCart(Long id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cant get shopping cart"));
        return shoppingCartMapper.toDto(shoppingCart);
    }

    private CartItem getCartItemFromUsersShoppingCart(Long id, Long userId) {
        ShoppingCart shoppingCart = getUsersShoppingCartById(userId);
        return shoppingCart.getCartItems().stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new AccessDeniedException("You do not have permission to "
                        + "delete this item"));
    }

    private ShoppingCart getUsersShoppingCartById(Long userId) {
        return shoppingCartRepository.findById(userId).get();
    }

    @Transactional
    public void clearShoppingCart(Long userId) {
        ShoppingCart shoppingCart = getUsersShoppingCartById(userId);
        shoppingCart.getCartItems().forEach(
                cartItem -> cartItemRepository.deleteById(cartItem.getId()));
        shoppingCart.setCartItems(new HashSet<>());
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart getCurrentUserShoppingCart() {
        return shoppingCartRepository.findById(userService.getCurrentUserId())
                .orElseThrow(() -> new RuntimeException("Can't find shopping cart"
                        + " by user id"));
    }
}
