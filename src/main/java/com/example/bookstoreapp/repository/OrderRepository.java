package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.model.Order;
import com.example.bookstoreapp.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser(User user);

    Optional<Order> findById(Long id);

    @EntityGraph(attributePaths = "orderItems")
    Optional<Order> findWithOrderItemsById(Long id);
}
