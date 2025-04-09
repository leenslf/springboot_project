package com.example.clickndine.service;

import com.example.clickndine.model.Restaurant;
import com.example.clickndine.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    // Retrieve a list of all approved restaurants
    public List<Restaurant> getApprovedRestaurants() {
        return restaurantRepository.findByStatus("APPROVED");
    }

    // Search restaurants by name and cuisine type; if either parameter is missing, use an empty string.
    public List<Restaurant> searchRestaurants(String name, String cuisine) {
        if(name == null) {
            name = "";
        }
        if(cuisine == null) {
            cuisine = "";
        }
        return restaurantRepository.findByStatusAndNameContainingIgnoreCaseAndCuisineTypeContainingIgnoreCase(
                "APPROVED", name, cuisine);
    }

    // Retrieve restaurant details by ID
    public Restaurant findById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
    }
}
