package com.example.bookstoreapp.mapper;

import com.example.bookstoreapp.config.MapperConfig;
import com.example.bookstoreapp.dto.OrderItemDto;
import com.example.bookstoreapp.model.CartItem;
import com.example.bookstoreapp.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(config = MapperConfig.class,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = BookMapper.class)
public interface OrderItemMapper {
    @Mapping(source = "book.id", target = "bookId")
    OrderItemDto toDto(OrderItem orderItem);

    OrderItem toEntity(OrderItemDto orderItemDto);

    @Mapping(source = "cartItem.book.id", target = "book.id")
    @Mapping(source = "cartItem.quantity", target = "quantity")
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "isDeleted", constant = "false")
    OrderItem toOrderItem(CartItem cartItem);
}
