package com.bhanu.ecommerce_backend.repository;

import com.bhanu.ecommerce_backend.model.CartItems;
import com.bhanu.ecommerce_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItems, Long> {
    List<CartItems> findByUser(User user);
}
