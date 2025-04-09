package com.example.clickndine.model;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String line1;
    private String city;
    private String state;
    private String zip;
    private String country;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Address() {}

    public Address(String line1, String city, String state, String zip, String country) {
        this.line1 = line1;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    // ...

    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
