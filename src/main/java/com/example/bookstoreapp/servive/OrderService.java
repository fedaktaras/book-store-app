package com.example.bookstoreapp.servive;

import com.example.bookstoreapp.dto.OrderDto;
import com.example.bookstoreapp.dto.PlaceOrderDto;
import com.example.bookstoreapp.dto.StatusDto;
import java.util.List;

public interface OrderService {
    List<OrderDto> getAllOrders();

    OrderDto createOrder(PlaceOrderDto placeOrderDto);

    OrderDto updateStatus(StatusDto statusDto);
}
