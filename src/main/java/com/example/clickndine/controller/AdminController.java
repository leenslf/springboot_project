package com.example.clickndine.controller;

import com.example.clickndine.model.Restaurant;
import com.example.clickndine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    // Admin endpoint to approve a restaurant registration.
    @PostMapping("/restaurants/{id}/approve")
    public ResponseEntity<Restaurant> approveRestaurant(@PathVariable Long id) {
        Restaurant restaurant = userService.approveRestaurant(id);
        return ResponseEntity.ok(restaurant);
    }


    // New endpoint: Reject restaurant registration.
    @PostMapping("/restaurants/{id}/reject")
    public ResponseEntity<Restaurant> rejectRestaurant(@PathVariable Long id) {
        Restaurant restaurant = userService.rejectRestaurant(id);
        return ResponseEntity.ok(restaurant);
    }

}
