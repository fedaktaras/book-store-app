package com.example.bookstoreapp.mapper;

import com.example.bookstoreapp.config.MapperConfig;
import com.example.bookstoreapp.dto.ShoppingCartDto;
import com.example.bookstoreapp.model.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(config = MapperConfig.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = CartItemMapper.class)
public interface ShoppingCartMapper {

    @Mapping(target = "cartItems", source = "shoppingCart.cartItems")
    ShoppingCartDto toDto(ShoppingCart shoppingCart);

    @Mapping(target = "cartItems", source = "shoppingCartDto.cartItems")
    ShoppingCart toEntity(ShoppingCartDto shoppingCartDto);
}
