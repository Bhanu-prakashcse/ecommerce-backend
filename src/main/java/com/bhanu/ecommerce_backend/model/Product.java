package com.bhanu.ecommerce_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double price;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name="image_url")
    private String imageUrl;

}
