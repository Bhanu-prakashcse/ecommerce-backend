package com.bhanu.ecommerce_backend.Service;

import com.bhanu.ecommerce_backend.dto.OrderResponse;
import com.bhanu.ecommerce_backend.model.*;
import com.bhanu.ecommerce_backend.repository.CartItemRepository;
import com.bhanu.ecommerce_backend.repository.OrderItemRepository;
import com.bhanu.ecommerce_backend.repository.OrderRepository;
import com.bhanu.ecommerce_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderService {
    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final UserRepository userRepo;
    @Autowired
    private final CartItemRepository cartRepo;
    @Autowired
    private OrderItemRepository orderItemRepository;



    public String PlaceOrder(String username){
        User user =userRepo.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        List<CartItems> cartItems=cartRepo.findByUser(user);
        if(cartItems.isEmpty()){
            return "Your cart is empty";
        }

        List<OrderItem> orderItems = cartItems.stream().map(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice((int) cartItem.getProduct().getPrice());
            return orderItem;
        }).toList();

        orderItemRepository.saveAll(orderItems);

        Order order = new Order();
        order.setUser(user);
        order.setItems(orderItems);
        order.setOrderDate(LocalDateTime.now());
        order.setExpectedDelivery(LocalDate.now().plusDays(5));
        order.setStatus(OrderStatus.PENDING);

        orderRepository.save(order);
        cartRepo.deleteAll(cartItems);

        int totalPrice = orderItems.stream()
                .mapToInt(item -> item.getPrice() * item.getQuantity())
                .sum();
        System.out.println("Order Placed for: " + user.getEmail() +
                " | Total: Rs." + totalPrice +
                " | Delivery: " + order.getExpectedDelivery());

        return "Order Placed Successfully";
    }

    public List<OrderResponse> getOrderHistory(String username){
        User user =userRepo.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        List<Order> orders = orderRepository.findByUser(user);

        return orders.stream().map(order -> {
            List<String> productNames = order.getItems().stream()
                    .map(item -> item.getProduct().getName())
                    .toList();
            return new OrderResponse(order.getId(), order.getOrderDate(), productNames);
        }).toList();
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public String updateOrderStatus(Long orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        orderRepository.save(order);
        return "Order Status updated to" + status;
    }

    public List<Order> getOrdersByUser(String username){
        User user=userRepo.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return orderRepository.findByUser(user);
    }
}
