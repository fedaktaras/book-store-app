package com.example.bookstoreapp.servive.impl;

import com.example.bookstoreapp.dto.OrderDto;
import com.example.bookstoreapp.dto.PlaceOrderDto;
import com.example.bookstoreapp.dto.StatusDto;
import com.example.bookstoreapp.mapper.OrderMapper;
import com.example.bookstoreapp.model.User;
import com.example.bookstoreapp.repository.OrderRepository;
import com.example.bookstoreapp.security.CustomUserDetailsService;
import com.example.bookstoreapp.servive.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private CustomUserDetailsService userDetailsService;
    private OrderMapper orderMapper;
    @Override
    public List<OrderDto> getAllOrders() {
        User user = userDetailsService.getCurrentUser();
        return orderRepository.findAllByUser(user)
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public OrderDto createOrder(PlaceOrderDto placeOrderDto) {
        return null;
    }

    @Override
    public OrderDto updateStatus(StatusDto statusDto) {
        return null;
    }
}
