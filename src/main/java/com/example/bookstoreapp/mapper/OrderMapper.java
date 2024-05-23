package com.example.bookstoreapp.mapper;

import com.example.bookstoreapp.config.MapperConfig;
import com.example.bookstoreapp.dto.OrderDto;
import com.example.bookstoreapp.dto.OrderItemDto;
import com.example.bookstoreapp.model.Order;
import com.example.bookstoreapp.model.OrderItem;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(config = MapperConfig.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = OrderItemMapper.class)
public abstract class OrderMapper {
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Mapping(source = "user.id", target = "userId")
    public abstract OrderDto toDto(Order order);

    @Mapping(source = "userId", target = "user.id")
    public abstract Order toEntity(OrderDto orderDto);

    protected Set<OrderItemDto> toOrderItemDtos(Set<OrderItem> orderItems) {
        return orderItems.stream()
                .map(orderItemMapper::toDto)
                .collect(Collectors.toSet());
    }

    protected Set<OrderItem> toOrderItems(Set<OrderItemDto> orderItemDtos) {
        return orderItemDtos.stream()
                .map(orderItemMapper::toEntity)
                .collect(Collectors.toSet());
    }
}
