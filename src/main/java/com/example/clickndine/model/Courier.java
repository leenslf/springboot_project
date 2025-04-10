package com.example.clickndine.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("COURIER")
public class Courier extends User {

    private String vehicleType;

//    @Embedded
//    private GeoPoint location;

    private boolean isAvailable;
    private float rating;

    public Courier() {
        super();
    }

    // This constructor calls the 5-arg constructor in User that includes userType
    // Make sure User has a matching constructor for (String, String, String, String, String)
    public Courier(String name, String email, String phone, String password) {
        super(name, email, phone, password, "COURIER");
        this.isAvailable = true; // default to available
        this.rating = 0.0f;      // default rating
    }

    // Additional getters/setters

    public String getVehicleType() {
        return vehicleType;
    }
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

//    public GeoPoint getLocation() {
//        return location;
//    }
//    public void setLocation(GeoPoint location) {
//        this.location = location;
//    }

    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public float getRating() {
        return rating;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }
}
