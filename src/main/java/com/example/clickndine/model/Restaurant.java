package com.example.clickndine.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("RESTAURANT")
public class Restaurant extends User {

    private String description;
    private String cuisineType;
    private float rating;
    private float minOrderVal;
    private String openingHours;

    // If you already have an Address embeddable, you can embed it; otherwise, you could simply use a String.
//    @Embedded
//    private Address address;

    // New field to track approval status when a new Restaurant registers.
    // Possible values: "PENDING", "APPROVED", "REJECTED"
    private String status;

    // Default constructor required by JPA
    public Restaurant() {
        super();
    }

    // Parameterized constructor to register a restaurant.
    // Note: This calls the User constructor that accepts (name, email, phone, password, userType)
    public Restaurant(String name, String email, String phone, String password) {
        super(name, email, phone, password, "RESTAURANT");
        // Set default status to PENDING on registration
        this.status = "PENDING";
    }

    // Getters and Setters for restaurant-specific fields

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getMinOrderVal() {
        return minOrderVal;
    }

    public void setMinOrderVal(float minOrderVal) {
        this.minOrderVal = minOrderVal;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

//    public Address getAddress() {
//        return address;
//    }
//
//    public void setAddress(Address address) {
//        this.address = address;
//    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
