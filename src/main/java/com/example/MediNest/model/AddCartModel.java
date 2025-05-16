package com.example.MediNest.model;

import lombok.Data;

import java.util.List;

@Data
public class AddCartModel {

    private String username;
    private List<CartModel> cartModel;

}
