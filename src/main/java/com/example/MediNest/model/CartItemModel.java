package com.example.MediNest.model;

import lombok.Data;

@Data
public class CartItemModel {

    private String productName;
    private double productPrice;
    private int quantity;

}
