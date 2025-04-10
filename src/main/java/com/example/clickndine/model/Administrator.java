package com.example.clickndine.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Administrator extends User {

    // If you want to add admin-specific fields, you can add them here.
    // For example, an admin might have a "department" or "accessLevel".
    // For now, we'll keep it simple.

    public Administrator() {
        super();
    }

    // This constructor calls the full constructor on User and sets userType as "ADMIN"
    public Administrator(String name, String email, String phone, String password) {
        super(name, email, phone, password, "ADMIN");
    }

    // You can add admin-specific methods if necessary in the future.
}
