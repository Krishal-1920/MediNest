package com.example.MediNest.mapper;

import com.example.MediNest.entity.Product;
import com.example.MediNest.model.ProductModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "productName", source = "productName")
    @Mapping(target = "productDescription", source = "productDescription")
    @Mapping(target = "productPrice", source = "productPrice")
    @Mapping(target = "productRating", source = "productRating")
    Product productModelToProduct(ProductModel productModel);

    @Mapping(target = "productName", source = "productName")
    @Mapping(target = "productDescription", source = "productDescription")
    @Mapping(target = "productPrice", source = "productPrice")
    @Mapping(target = "productRating", source = "productRating")
    ProductModel productToProductModel(Product product);

    List<ProductModel> productListToProductModelList(List<Product> products);

    @Mapping(target = "productName", source = "productModel.productName")
    @Mapping(target = "productDescription", source = "productModel.productDescription")
    @Mapping(target = "productPrice", source = "productModel.productPrice")
    @Mapping(target = "productRating", source = "productModel.productRating")
    Product updateProductModel(ProductModel productModel, Product productById);

}
