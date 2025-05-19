package com.example.MediNest.repository;

import com.example.MediNest.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {

    List<Cart> findByUserUserId(String userId);

    Optional<Cart> findByUserUserIdAndProductProductId(String userId, String productId);
}
