package com.example.MediNest.controller;

import com.example.MediNest.model.BillingModel;
import com.example.MediNest.service.BillingService;
import com.example.MediNest.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/billing")
public class BillingController {

    private final BillingService billingService;

    private final JwtUtil jwtUtil;

    @GetMapping("/generateBill")
    public ResponseEntity<BillingModel> generateBill(@RequestHeader("Authorization") String tokenHeader){
        String authenticatedEmail = jwtUtil.extractUsername(tokenHeader);
        return ResponseEntity.ok(billingService.generateBill(authenticatedEmail));
    }
}
