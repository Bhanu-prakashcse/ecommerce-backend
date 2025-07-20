package com.bhanu.ecommerce_backend.Controller;

import com.bhanu.ecommerce_backend.Service.ProductService;
import com.bhanu.ecommerce_backend.dto.ProductDTO;
import com.bhanu.ecommerce_backend.model.Product;
import com.bhanu.ecommerce_backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public  ResponseEntity<String> addProduct(@RequestBody ProductDTO dto) {
        productService.saveProduct(dto);
        return ResponseEntity.ok("Successfully added product");
    }

    @GetMapping("/all")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){
        List<Product> results=productService.searchProducts(keyword);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filterByPrice(@RequestParam double min, @RequestParam double max) {
        return ResponseEntity.ok(productService.filterByPrice(min, max));
    }

    @GetMapping("/category/{name}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String name) {
        List<Product> products = productService.getProductByCategory(name);
        return ResponseEntity.ok(products);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody ProductDTO dto) {
        Product updated = productService.updateProduct(id, dto);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody ProductDTO dto) {
        productService.updateProduct(id, dto);
        return ResponseEntity.ok("Product updated successfully");
    }

    @PutMapping("/update-image")
    public ResponseEntity<String> updateProductImage(@RequestBody Map<String, String> payLoad) {
        String name = payLoad.get("name");
        String imageUrl = payLoad.get("imageUrl");

        Optional<Product> optionalProduct = productRepository.findByName(name);
        if(optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setImageUrl(imageUrl);
            productRepository.save(product);
            return ResponseEntity.ok("✅ Image updated for product: " + name);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ Product not found with name: " + name);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("successfully deleted");
    }
}
