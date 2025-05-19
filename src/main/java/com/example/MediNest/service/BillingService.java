package com.example.MediNest.service;

import com.example.MediNest.entity.Cart;
import com.example.MediNest.entity.Product;
import com.example.MediNest.entity.User;
import com.example.MediNest.exceptions.DataNotFoundException;
import com.example.MediNest.model.BillingModel;
import com.example.MediNest.model.CartItemModel;
import com.example.MediNest.repository.CartRepository;
import com.example.MediNest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillingService {

    private final UserRepository userRepository;

    private final CartRepository cartRepository;

    public BillingModel generateBill(String userId){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User Not Found"));

        List<Cart> userCart = cartRepository.findByUserUserId(userId);
        List<CartItemModel> myCart = new ArrayList<>();
        double totalPrice = 0;

        for(Cart cart : userCart){
            double productPrice = cart.getProduct().getProductPrice();
            int quantity = cart.getQuantity();
            double individualTotal = productPrice * quantity;

            CartItemModel cartItemModel = new CartItemModel();
            cartItemModel.setProductName(cart.getProduct().getProductName());
            cartItemModel.setProductPrice(cart.getProduct().getProductPrice());
            cartItemModel.setQuantity(cart.getQuantity());

            myCart.add(cartItemModel);
            totalPrice = individualTotal + totalPrice;
        }
        BillingModel billingModel = new BillingModel();
        billingModel.setUsername(user.getUsername());
        billingModel.setPhoneNumber(user.getPhoneNumber());
        billingModel.setAddress(user.getAddress());
        billingModel.setCartItems(myCart);
        billingModel.setTotalPrice(totalPrice);
        return billingModel;
    }

}
