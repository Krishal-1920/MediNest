package com.example.MediNest.controller;

import com.example.MediNest.model.UserModel;
import com.example.MediNest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<UserModel> signUp(@RequestBody UserModel userModel){
        return ResponseEntity.ok(userService.signUp(userModel));
    }

    @DeleteMapping("/deleteAccount/{userId}")
    public void deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
    }

    @GetMapping("/getAllUsers")

}
