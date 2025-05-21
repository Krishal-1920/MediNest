package com.example.MediNest.controller;

import com.example.MediNest.model.AddCartModel;
import com.example.MediNest.model.CartModel;
import com.example.MediNest.service.CartService;
import com.example.MediNest.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    private final JwtUtil jwtUtil;

    @PostMapping("/addToCart")
    public ResponseEntity<CartModel> addItemToCart(@RequestHeader("Authorization") String tokenHeader,
                                                   @RequestParam String productId){
        String authenticatedEmail = jwtUtil.extractUsername(tokenHeader);
        return ResponseEntity.ok(cartService.addItemToCart(authenticatedEmail, productId));
    }

    @DeleteMapping("/deleteItems")
    public void deleteItem(@RequestHeader("Authorization") String tokenHeader,
                           @RequestParam String cartId){
        String authenticatedEmail = jwtUtil.extractUsername(tokenHeader);
        cartService.deleteItemById(cartId);
    }

    @GetMapping("/getAllTheItems")
    public ResponseEntity<AddCartModel> getAllItemsInCart(@RequestHeader("Authorization") String tokenHeader) {
        String authenticatedEmail = jwtUtil.extractUsername(tokenHeader);
        return ResponseEntity.ok(cartService.getCartDetails(authenticatedEmail));
    }

}
