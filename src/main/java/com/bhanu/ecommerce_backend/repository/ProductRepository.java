package com.bhanu.ecommerce_backend.repository;

import com.bhanu.ecommerce_backend.model.Category;
import com.bhanu.ecommerce_backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByPriceBetween(double minPrice, double maxPrice);
    List<Product> findByNameContainingIgnoreCase(String keyword);

    List<Product> findByCategory_NameIgnoreCase(String name);

    Optional<Product> findByName(String name);


    List<Product> findByCategory(Category category);
    List<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);

}
