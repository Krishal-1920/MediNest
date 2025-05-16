package com.example.MediNest.model;

import lombok.Data;

@Data
public class CartModel {

    private String username;
    private String productName;
    private double productPrice;
    private int quantity;

}
