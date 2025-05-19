package com.example.MediNest.controller;

import com.example.MediNest.model.BillingModel;
import com.example.MediNest.service.BillingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/billing")
public class BillingController {

    private final BillingService billingService;

    @GetMapping("/getAllTheItems")
    public ResponseEntity<BillingModel> generateBill(@RequestParam String userId){
        return ResponseEntity.ok(billingService.generateBill(userId));
    }
}
