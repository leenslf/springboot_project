package com.example.clickndine.model;

import jakarta.persistence.*;

/**
 * Represents a user in the Click & Dine system.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    @Column(nullable = false)
    private String password;  // In production, store hashed passwords

    @Column(name = "user_type", nullable = false)
    private String userType;  // e.g., "customer", "courier", "administrator", "restaurant"

    // Default constructor (required by JPA)
    public User() {}

    // Parameterized constructor for convenience
    public User(String name, String email, String phone, String password, String userType) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.userType = userType;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
