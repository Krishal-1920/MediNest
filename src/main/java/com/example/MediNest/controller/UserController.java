package com.example.MediNest.controller;

import com.example.MediNest.model.UserModel;
import com.example.MediNest.service.CustomUserDetailsService;
import com.example.MediNest.service.UserService;
import com.example.MediNest.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final CustomUserDetailsService userDetailsService;

    @PostMapping("/signUp")
    public ResponseEntity<UserModel> signUp(@RequestBody UserModel userModel){
        return ResponseEntity.ok(userService.signUp(userModel));
    }

    @DeleteMapping("/deleteAccount")
    public void deleteUser(@RequestHeader("Authorization") String tokenHeader){
        String authenticatedEmail = jwtUtil.extractUsername(tokenHeader);
        userService.deleteUser(authenticatedEmail);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserModel>> getUsers(@RequestParam String search,
                                                    @RequestHeader("Authorization") String tokenHeader){
        String authenticatedEmail = jwtUtil.extractUsername(tokenHeader);
        return ResponseEntity.ok(userService.getAllUsers(search));
    }

    @PutMapping("/updateDetails")
    public ResponseEntity<UserModel> updateProfile(@RequestHeader("Authorization") String tokenHeader,
                                                   @RequestBody UserModel userModel) {
        String authenticatedEmail = jwtUtil.extractUsername(tokenHeader);
        return ResponseEntity.ok(userService.updateProfile(authenticatedEmail, userModel));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserModel userModel) {
        try {
            // Wrap email and password into UsernamePasswordAuthenticationToken
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userModel.getEmail(), userModel.getPassword()));

            UserDetails userDetails = userDetailsService.loadUserByUsername(userModel.getEmail());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return ResponseEntity.ok(jwt);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect Email or Password");
        }
    }

}
