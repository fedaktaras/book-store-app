package com.example.bookstoreapp.mapper;

import com.example.bookstoreapp.config.MapperConfig;
import com.example.bookstoreapp.dto.ShoppingCartDto;
import com.example.bookstoreapp.model.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(config = MapperConfig.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = CartItemMapper.class)
public interface ShoppingCartMapper {

    ShoppingCartDto toDto(ShoppingCart shoppingCart);

    ShoppingCart toEntity(ShoppingCartDto shoppingCartDto);
}
