package com.bhanu.ecommerce_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> items;
    private LocalDateTime orderDate;
    private LocalDate expectedDelivery;

    @Enumerated(EnumType.STRING)
    private OrderStatus status =  OrderStatus.PENDING;

    private String paymentMethod;
}
