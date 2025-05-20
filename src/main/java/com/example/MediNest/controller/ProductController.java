package com.example.MediNest.controller;

import com.example.MediNest.entity.Product;
import com.example.MediNest.model.ProductModel;
import com.example.MediNest.service.ProductService;
import com.example.MediNest.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    private final JwtUtil jwtUtil;

    @PostMapping("/addItem")
    public ResponseEntity<ProductModel> addProduct(@RequestHeader("Authorization") String tokenHeader,
                                                   @RequestBody ProductModel productModel){
        String authenticatedEmail = jwtUtil.extractUsername(tokenHeader);
        return ResponseEntity.ok(productService.addProduct(productModel));
    }

    @GetMapping("/getMedicine")
    public ResponseEntity<List<ProductModel>> productList(@RequestHeader("Authorization") String tokenHeader,
                                                          @RequestParam String search){
        String authenticatedEmail = jwtUtil.extractUsername(tokenHeader);
        return ResponseEntity.ok(productService.productList(search));
    }

    @PutMapping("/updateItem")
    public ResponseEntity<ProductModel> updateProduct(@RequestHeader("Authorization") String tokenHeader,
                                                      @RequestParam String productId,
                                                     @RequestBody ProductModel productModel) {
        String authenticatedEmail = jwtUtil.extractUsername(tokenHeader);
        return ResponseEntity.ok(productService.updateProduct(productId, productModel));
    }

    @DeleteMapping("/deleteItem")
    public void deleteProduct(@RequestHeader("Authorization") String tokenHeader,
                              @RequestParam String productId){
        String authenticatedEmail = jwtUtil.extractUsername(tokenHeader);
        productService.deleteProduct(productId);
    }

}
