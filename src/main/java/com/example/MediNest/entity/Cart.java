package com.example.MediNest.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cart_id", updatable = false, nullable = false)
    private String cartId;

    @Column(name = "quantity")
    private Integer quantity;

    // Mapping

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

}
