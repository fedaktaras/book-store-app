package com.example.bookstoreapp.servive.impl;

import com.example.bookstoreapp.dto.OrderDto;
import com.example.bookstoreapp.dto.OrderItemDto;
import com.example.bookstoreapp.dto.PlaceOrderDto;
import com.example.bookstoreapp.dto.StatusDto;
import com.example.bookstoreapp.mapper.OrderItemMapper;
import com.example.bookstoreapp.mapper.OrderMapper;
import com.example.bookstoreapp.model.Order;
import com.example.bookstoreapp.model.OrderItem;
import com.example.bookstoreapp.model.ShoppingCart;
import com.example.bookstoreapp.model.Status;
import com.example.bookstoreapp.repository.OrderRepository;
import com.example.bookstoreapp.repository.ShoppingCartRepository;
import com.example.bookstoreapp.servive.OrderService;
import com.example.bookstoreapp.servive.ShoppingCartService;
import com.example.bookstoreapp.servive.UserService;
import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    public List<OrderDto> getAllOrders(Long id) {
        List<Order> allByUser = orderRepository.findByUserId(id);
        return allByUser.stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public OrderDto createOrder(PlaceOrderDto placeOrderDto, Long userId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("Can't find shopping cart bu id: " + userId));
        if (shoppingCart.getCartItems().isEmpty()) {
            throw new EntityNotFoundException("Empty shopping cart id: " + userId);
        }
        Order order = createNewOrder(placeOrderDto, shoppingCart);
        Order saved = orderRepository.save(order);
        shoppingCartService.clearShoppingCart();
        return orderMapper.toDto(saved);
    }

    @Override
    public OrderDto updateStatus(StatusDto statusDto, Long id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Could not find find order wit id: " + id)
        );
        order.setStatus(statusDto.getStatus());
        Order saved = orderRepository.save(order);
        return orderMapper.toDto(saved);
    }

    @Override
    public OrderItemDto getOrderItemFromOrder(Long orderId, Long orderItemId, Long userId) {
        OrderItem orderItem = orderRepository.findOrderItemByIdAndOrderIdAndUserId(
                orderItemId, orderId, userId
        ).orElseThrow(() -> new EntityNotFoundException("Can't find order item with id: "
                                + orderItemId + " in order with id"));
        return orderItemMapper.toDto(orderItem);
    }

    @Override
    public OrderDto getOrder(Long orderId, Long userId) {
        Order order = orderRepository.findWithOrderItemsByIdAndUserId(orderId, userId)
                .orElseThrow(() -> new EntityNotFoundException("Can't find your order with id: "
                        + orderId));
        return orderMapper.toDto(order);
    }

    private Order createNewOrder(PlaceOrderDto orderDto, ShoppingCart cart) {
        Order order = new Order();
        order.setUser(userService.getCurrentUser());
        order.setStatus(Status.PENDING);
        order.setTotal(calculateTotalPrice(cart));
        order.setOrderDate(LocalDateTime.now());
        order.setShippingAddress(orderDto.getShippingAddress());
        Set<OrderItem> orderItems = cart.getCartItems().stream()
                .map(orderItemMapper::toOrderItem)
                .peek(orderItem -> orderItem.setOrder(order))
                .peek(orderItem -> orderItem.setPrice(orderItem.getBook().getPrice()))
                .collect(Collectors.toSet());
        order.setOrderItems(orderItems);
        return order;
    }

    private BigDecimal calculateTotalPrice(ShoppingCart cart) {
        return cart.getCartItems().stream()
                .map(i -> i.getBook()
                        .getPrice()
                        .multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
