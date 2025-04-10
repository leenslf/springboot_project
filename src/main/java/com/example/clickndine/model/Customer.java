package com.example.clickndine.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("CUSTOMER")
public class Customer extends User {

    // One-to-many for addresses
    // A typical approach: store addresses in a separate table with a foreign key to Customer
//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Address> addresses = new ArrayList<>();

    // Many-to-many for favorite restaurants
    // By default, JPA will create a bridging table, e.g. customer_favorites
    @ManyToMany
    @JoinTable(
            name = "customer_favorites",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "restaurant_id")
    )
    private List<Restaurant> favorites = new ArrayList<>();

    // One-to-many for order history
    // A single customer can have multiple orders
//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Order> orderHistory = new ArrayList<>();

    public Customer() {
        super();
    }

    public Customer(String name, String email, String phone, String password) {
        super(name, email, phone, password,"CUSTOMER" );
    }

    // Getters and Setters

//    public List<Address> getAddresses() {
//        return addresses;
//    }
//
//    public void setAddresses(List<Address> addresses) {
//        this.addresses = addresses;
//    }

    public List<Restaurant> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Restaurant> favorites) {
        this.favorites = favorites;
    }

//    public List<Order> getOrderHistory() {
//        return orderHistory;
//    }
//
//    public void setOrderHistory(List<Order> orderHistory) {
//        this.orderHistory = orderHistory;
//    }

    // Example convenience methods:

//    public void addAddress(Address address) {
//        this.addresses.add(address);
//        address.setCustomer(this);
//    }

//    public void removeAddress(Address address) {
//        this.addresses.remove(address);
//        address.setCustomer(null);
//    }

    public void addFavorite(Restaurant restaurant) {
        this.favorites.add(restaurant);
    }

    public void removeFavorite(Restaurant restaurant) {
        this.favorites.remove(restaurant);
    }

//    public void addOrder(Order order) {
//        this.orderHistory.add(order);
//        order.setCustomer(this);
//    }
}
