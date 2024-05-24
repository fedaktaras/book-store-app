package com.example.bookstoreapp.servive;

import com.example.bookstoreapp.dto.OrderDto;
import com.example.bookstoreapp.dto.OrderItemDto;
import com.example.bookstoreapp.dto.PlaceOrderDto;
import com.example.bookstoreapp.dto.StatusDto;
import java.util.List;

public interface OrderService {
    List<OrderDto> getAllOrders();

    public OrderDto createOrder(PlaceOrderDto placeOrderDto, Long id);

    OrderDto updateStatus(StatusDto statusDto, Long id);

    OrderItemDto getOrderItemFromOrder(Long orderId, Long orderItemId);

    OrderDto getOrder(Long orderId);
}
