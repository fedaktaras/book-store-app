package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.model.Order;
import com.example.bookstoreapp.model.OrderItem;
import com.example.bookstoreapp.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser(User user);

    List<Order> findByUserId(Long id);

    Optional<Order> findById(Long id);

    @EntityGraph(attributePaths = "orderItems")
    Optional<Order> findWithOrderItemsByIdAndUserId(Long id, Long userId);

    @Query("SELECT oi FROM OrderItem oi WHERE oi.id = :orderItemId "
            + "AND oi.order.id = :orderId AND oi.order.user.id = :userId")
    Optional<OrderItem> findOrderItemByIdAndOrderIdAndUserId(@Param("orderItemId") Long orderItemId,
                                                             @Param("orderId") Long orderId,
                                                             @Param("userId") Long userId);
}
