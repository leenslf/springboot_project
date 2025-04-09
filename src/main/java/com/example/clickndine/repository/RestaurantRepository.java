package com.example.clickndine.repository;

import com.example.clickndine.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    // Return all restaurants where status is "APPROVED"
    List<Restaurant> findByStatus(String status);

    // Search approved restaurants by name and cuisine type (both partially matching)
    List<Restaurant> findByStatusAndNameContainingIgnoreCaseAndCuisineTypeContainingIgnoreCase(
            String status,
            String name,
            String cuisineType);
}
