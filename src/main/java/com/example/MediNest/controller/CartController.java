package com.example.MediNest.controller;

import com.example.MediNest.model.AddCartModel;
import com.example.MediNest.model.CartModel;
import com.example.MediNest.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping("/addToCart")
    public ResponseEntity<CartModel> addItemToCart(@RequestParam String userId,
                                                   @RequestParam String productId){
        return ResponseEntity.ok(cartService.addItemToCart(userId, productId));
    }

    @DeleteMapping("/deleteItems/{cartId}")
    public void deleteItem(@PathVariable String cartId){
        cartService.deleteItemById(cartId);
    }

    @GetMapping("/getAllTheItems")
    public ResponseEntity<AddCartModel> getAllItemsInCart(@RequestParam String userId) {
        return ResponseEntity.ok(cartService.getCartDetails(userId));
    }

}
