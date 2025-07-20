package com.bhanu.ecommerce_backend.Service;

import com.bhanu.ecommerce_backend.model.CartItems;
import com.bhanu.ecommerce_backend.model.Product;
import com.bhanu.ecommerce_backend.model.User;
import com.bhanu.ecommerce_backend.repository.CartItemRepository;
import com.bhanu.ecommerce_backend.repository.ProductRepository;
import com.bhanu.ecommerce_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartItemRepository cartRepo;
    private final ProductRepository productRepo;
    private final UserRepository userRepo;

    public String addToCart(String username, Long productId, int quantity) {
        User user=userRepo.findByUsername(username).orElseThrow();
        Product product=productRepo.findById(productId).orElseThrow();

        CartItems item=new  CartItems();
        item.setProduct(product);
        item.setQuantity(quantity);
        item.setUser(user);
        cartRepo.save(item);
        return "Product added to cart!";
    }

    public List<CartItems> getCart(String username) {
        User user=userRepo.findByUsername(username).orElseThrow();
        return cartRepo.findByUser(user);
    }

    public String removeCartItem(Long cartItemID, String username) {
        User user=userRepo.findByUsername(username).orElseThrow();
        CartItems item=cartRepo.findById(cartItemID).orElseThrow();

        if(!item.getUser().getId().equals(user.getId())){
            throw new RuntimeException("Unauthorized attempt to remove item from cart.");
        }
        cartRepo.delete(item);
        return "Item removed from cart!";
    }
}
