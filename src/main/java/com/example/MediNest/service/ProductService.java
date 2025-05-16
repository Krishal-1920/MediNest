package com.example.MediNest.service;

import com.example.MediNest.entity.Product;
import com.example.MediNest.mapper.ProductMapper;
import com.example.MediNest.model.ProductModel;
import com.example.MediNest.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductMapper productMapper;

    private final ProductRepository productRepository;

    public ProductModel addProduct(ProductModel productModel) {
        Product product = productMapper.productModelToProduct(productModel);
        Product savedProduct = productRepository.save(product);
        return productMapper.productToProductModel(savedProduct);
    }

    public List<ProductModel> productList(String search) {
        List<Product> products = productRepository.searchProducts(search);

        return productMapper.productListToProductModelList(products);


    }

    public ProductModel updateProduct(String productId, ProductModel productModel) {
        Product productById = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));
        productMapper.updateProductModel(productModel, productById);
        productById.setProductId(productId);
        Product updateProduct = productRepository.save(productById);
        return productMapper.productToProductModel(updateProduct);
    }

    public void deleteProduct(String productId) {
        Product productById = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));
        productRepository.delete(productById);
    }
}
