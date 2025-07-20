package com.bhanu.ecommerce_backend.Controller;


import com.bhanu.ecommerce_backend.Service.OrderService;
import com.bhanu.ecommerce_backend.dto.OrderResponse;
import com.bhanu.ecommerce_backend.model.Order;
import com.bhanu.ecommerce_backend.model.OrderStatus;
import com.bhanu.ecommerce_backend.repository.OrderItemRepository;
import com.bhanu.ecommerce_backend.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;

    @GetMapping("/history")
    public ResponseEntity<List<OrderResponse>> getOrderHistory(Principal principal) {
        String username = principal.getName();
        return ResponseEntity.ok(orderService.getOrderHistory(username));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/my")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public ResponseEntity<List<Order>> getMyOrders(Principal principal) {
        String username = principal.getName();
        return ResponseEntity.ok(orderService.getOrdersByUser(username));
    }

    @PutMapping("/update-status/{orderId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> updateStatus(@PathVariable Long orderId, @RequestParam OrderStatus status) {
        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, status));
    }


    @PutMapping("/admin/orders/{orderId}/status")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> updateOrderStatus(@PathVariable Long orderId, @RequestParam OrderStatus status) {
        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, status));
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
        // Delete order items first
        orderItemRepository.deleteByOrderId(orderId);

        // Then delete order itself
        orderRepository.deleteById(orderId);

        return ResponseEntity.ok("Order and its items deleted successfully");
    }


    @DeleteMapping("/order-item/delete/{orderId}")
    public ResponseEntity<String> deleteOrderItemsByOrderId(@PathVariable Long orderId) {
        try {
            orderItemRepository.deleteByOrderId(orderId);
            return ResponseEntity.ok("✅ Order items deleted successfully for Order ID: " + orderId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("❌ Failed to delete order items.");
        }
    }

}
