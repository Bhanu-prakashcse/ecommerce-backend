package com.bhanu.ecommerce_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor

public class OrderResponse {
    private Long orderId;
    private LocalDateTime orderDate;
    private List<String> products;
}
