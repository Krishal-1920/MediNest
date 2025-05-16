package com.example.MediNest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_id", updatable = false, nullable = false)
    private String productId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_description", nullable = false)
    private String productDescription;

    @Column(name = "productPrice")
    private double productPrice;

    @Column(name = "product_rating")
    @Min(value = 0)
    @Max(value = 5)
    private float productRating;

    // Mapping

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Cart> carts;

}
