package com.bhanu.ecommerce_backend.repository;

import com.bhanu.ecommerce_backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByNameIgnoreCase(String name);
   // Optional<Category> findbyname(String name);

}
