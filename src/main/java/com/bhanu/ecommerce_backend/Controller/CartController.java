package com.bhanu.ecommerce_backend.Controller;

import com.bhanu.ecommerce_backend.Service.CartService;
import com.bhanu.ecommerce_backend.Service.OrderService;
import com.bhanu.ecommerce_backend.Service.smsService;
import com.bhanu.ecommerce_backend.model.CartItems;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor

public class CartController {
    private final CartService cartService;
    private final OrderService orderService;
    private final smsService smsService;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<String> addToCart(
            @RequestParam Long productId,
            @RequestParam int quantity,
            Principal principal)
    {
        String username=principal.getName();
        return ResponseEntity.ok(cartService.addToCart(username, productId, quantity));
    }


    @PostMapping("/place-order")
    public ResponseEntity<String> placeOrder(Principal principal){
        String username=principal.getName();
        return ResponseEntity.ok(orderService.PlaceOrder(username));
    }

    @GetMapping("/view")
    public ResponseEntity<List<CartItems>> getCart(Authentication authentication, Principal principal) {
        String username=principal.getName();
        return ResponseEntity.ok(cartService.getCart(username));
    }

    @GetMapping("/test-sms")
    public String testSms() {
        smsService.sendSms("+91XXXXXXXXXX", "Test message from Twilio");
        return "Sent!";
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> removeItem(@PathVariable Long id, Principal principal) {
        String username=principal.getName();
        return ResponseEntity.ok(cartService.removeCartItem(id, username));
    }

}
