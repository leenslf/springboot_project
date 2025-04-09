package com.example.clickndine.controller;

import com.example.clickndine.model.Restaurant;
import com.example.clickndine.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    // Endpoint to list all approved restaurants.
    @GetMapping
    public ResponseEntity<List<Restaurant>> listApprovedRestaurants() {
        List<Restaurant> restaurants = restaurantService.getApprovedRestaurants();
        return ResponseEntity.ok(restaurants);
    }

    // Endpoint to search restaurants by name and/or cuisine.
    // Query parameters (both optional): name and cuisine.
    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurants(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String cuisine) {
        List<Restaurant> results = restaurantService.searchRestaurants(name, cuisine);
        return ResponseEntity.ok(results);
    }

    // Endpoint to retrieve full details of a specific restaurant by its ID.
    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantDetails(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.findById(id);
        return ResponseEntity.ok(restaurant);
    }
}
