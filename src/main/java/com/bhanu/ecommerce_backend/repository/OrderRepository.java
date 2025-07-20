package com.bhanu.ecommerce_backend.repository;

import com.bhanu.ecommerce_backend.model.Order;
import com.bhanu.ecommerce_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}