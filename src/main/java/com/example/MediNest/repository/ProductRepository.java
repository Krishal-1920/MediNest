package com.example.MediNest.repository;

import com.example.MediNest.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    @Query(value = "SELECT * FROM products " +
            "WHERE (:search IS NULL OR " +
            "LOWER(product_name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(product_description) LIKE LOWER(CONCAT('%', :search, '%')))",
            nativeQuery = true)
    List<Product> searchProducts(@Param("search") String search);


}
