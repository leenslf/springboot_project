package com.example.clickndine.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // For simplicity, we're storing the customer ID directly.
    // Alternatively, you can establish a relationship to a Customer entity.
    private Long customerId;

    // One-to-Many relationship with CartItem.
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cart_id")
    private List<CartItem> items = new ArrayList<>();

    public Cart() {}

    public Cart(Long customerId) {
        this.customerId = customerId;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) { this.id = id; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public List<CartItem> getItems() { return items; }
    public void setItems(List<CartItem> items) { this.items = items; }

    // Convenience method to add an item
    public void addItem(CartItem item) {
        this.items.add(item);
    }

    // You can add methods to remove items, update quantities, etc.
}
