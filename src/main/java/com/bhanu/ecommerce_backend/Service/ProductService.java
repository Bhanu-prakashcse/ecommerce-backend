package com.bhanu.ecommerce_backend.Service;

import com.bhanu.ecommerce_backend.dto.ProductDTO;
import com.bhanu.ecommerce_backend.model.Category;
import com.bhanu.ecommerce_backend.model.Product;
import com.bhanu.ecommerce_backend.repository.CategoryRepository;
import com.bhanu.ecommerce_backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public void saveProduct(ProductDTO dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found: " + dto.getCategoryId()));

        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        product.setCategory(category);

        productRepository.save(product);
    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Product getProductById(long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("product not found"));
    }
    public Product updateProduct(long productId, ProductDTO dto) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("product not found"));
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQuantity(dto.getQuantity());
        product.setImageUrl(dto.getImageUrl());

        Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new RuntimeException("category not found"));
        product.setCategory(category);
        return productRepository.save(product);

    }
    public Product deleteProduct(long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("product not found"));
        productRepository.deleteById(id);
        return product;
    }

    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
    }

    public List<Product> filterByPrice(double min, double max) {
        return productRepository.findByPriceBetween(min, max);
    }

    public List<Product> getProductByCategory(String categoryName) {
        Category category=categoryRepository.findByNameIgnoreCase(categoryName).orElseThrow(() -> new RuntimeException("Category not found"));
        return productRepository.findByCategory(category);
    }
}
