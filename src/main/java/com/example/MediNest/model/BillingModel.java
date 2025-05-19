package com.example.MediNest.model;

import lombok.Data;

import java.util.List;

@Data
public class BillingModel {

    private String username;
    private String phoneNumber;
    private String address;
    private List<CartItemModel> cartItems;
    private double totalPrice;

}
