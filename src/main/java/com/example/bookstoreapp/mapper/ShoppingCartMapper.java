package com.example.bookstoreapp.mapper;

import com.example.bookstoreapp.config.MapperConfig;
import com.example.bookstoreapp.dto.CartItemDto;
import com.example.bookstoreapp.dto.ShoppingCartDto;
import com.example.bookstoreapp.model.CartItem;
import com.example.bookstoreapp.model.ShoppingCart;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(config = MapperConfig.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = CartItemMapper.class)
public abstract class ShoppingCartMapper {
    @Autowired
    CartItemMapper cartItemMapper;
    public abstract ShoppingCartDto toDto(ShoppingCart shoppingCart);

    public abstract ShoppingCart toEntity(ShoppingCartDto shoppingCartDto);


    public Set<CartItem> dtosToEntities(Set<CartItemDto> cartItemDtos) {
        return cartItemDtos.stream()
                .map(cartItemMapper::toEntity)
                .collect(Collectors.toSet());
    }

    public Set<CartItemDto> entitiesToDtos(Set<CartItem> cartItems) {
        return cartItems.stream()
                .map(cartItemMapper::toDto)
                .collect(Collectors.toSet());
    }
}
