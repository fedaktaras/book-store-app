package com.example.bookstoreapp.servive.impl;

import com.example.bookstoreapp.dto.CartItemDto;
import com.example.bookstoreapp.dto.OrderDto;
import com.example.bookstoreapp.dto.OrderItemDto;
import com.example.bookstoreapp.dto.PlaceOrderDto;
import com.example.bookstoreapp.dto.ShoppingCartDto;
import com.example.bookstoreapp.dto.StatusDto;
import com.example.bookstoreapp.mapper.OrderItemMapper;
import com.example.bookstoreapp.mapper.OrderMapper;
import com.example.bookstoreapp.model.Order;
import com.example.bookstoreapp.model.OrderItem;
import com.example.bookstoreapp.model.Status;
import com.example.bookstoreapp.model.User;
import com.example.bookstoreapp.repository.BookRepository;
import com.example.bookstoreapp.repository.OrderRepository;
import com.example.bookstoreapp.servive.OrderService;
import com.example.bookstoreapp.servive.ShoppingCartService;
import com.example.bookstoreapp.servive.UserService;
import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final BookRepository bookRepository;
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    @Override
    public List<OrderDto> getAllOrders() {
        User user = userService.getCurrentUser();
        return orderRepository.findAllByUser(user)
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public OrderDto createOrder(PlaceOrderDto placeOrderDto, Long userId) {
        ShoppingCartDto shoppingCartDto = shoppingCartService.getShoppingCart(userId);
        Order order = orderMapper.toEntity(mapShoppingCartDtoToOrderDto(shoppingCartDto));
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
    public OrderDto getOrder(Long orderId) {
        Order order = orderRepository.findWithOrderItemsById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Can't find order with id: "
                        + orderId));
        orderOwnerValidator(order);
        return orderMapper.toDto(order);
    }

    private OrderDto mapShoppingCartDtoToOrderDto(ShoppingCartDto shoppingCartDto) {
        OrderDto orderDto = new OrderDto();
        orderDto.setUserId(orderDto.getUserId());

        BigDecimal total = BigDecimal.ZERO;
        List<OrderItemDto> orderItems = new ArrayList<>();
        for (CartItemDto cartItem : shoppingCartDto.getCartItems()) {
            BigDecimal price = bookRepository.getPriceByBookId(cartItem.getBookId());
            BigDecimal itemTotal = price.multiply(BigDecimal.valueOf(cartItem.getQuantity()));
            total = total.add(itemTotal);
            OrderItemDto orderItem = new OrderItemDto(cartItem);
            orderItems.add(orderItem);
        }
        orderDto.setOrderItems(orderItems);
        orderDto.setTotal(total);
        orderDto.setStatus(Status.PENDING.name());

        return orderDto;
    }

    private void orderOwnerValidator(Order order) {
        if (!order.getId().equals(userService.getCurrentUserId())) {
            throw new AccessDeniedException("You dont have access to this order");
        }
    }
}
