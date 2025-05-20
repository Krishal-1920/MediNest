package com.example.MediNest.controller;

import com.example.MediNest.entity.Product;
import com.example.MediNest.model.ProductModel;
import com.example.MediNest.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/addItem")
    public ResponseEntity<ProductModel> addProduct(@RequestBody ProductModel productModel){
        return ResponseEntity.ok(productService.addProduct(productModel));
    }

    @GetMapping("/getMedicine")
    public ResponseEntity<List<ProductModel>> productList(@RequestParam String search){
        return ResponseEntity.ok(productService.productList(search));
    }

    @PutMapping("/updateItem")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable String productId,
                                                     @RequestBody ProductModel productModel) {
        return ResponseEntity.ok(productService.updateProduct(productId, productModel));
    }

    @DeleteMapping("/deleteItem")
    public void deleteProduct(@PathVariable String productId){
        productService.deleteProduct(productId);
    }

}
