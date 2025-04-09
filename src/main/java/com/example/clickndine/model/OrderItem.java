package com.example.clickndine.model;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long foodItemId;
    private String foodItemName;
    private double price;
    private int quantity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem() {}

    public OrderItem(Long foodItemId, String foodItemName, double price, int quantity) {
        this.foodItemId = foodItemId;
        this.foodItemName = foodItemName;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getFoodItemId() { return foodItemId; }
    public void setFoodItemId(Long foodItemId) { this.foodItemId = foodItemId; }

    public String getFoodItemName() { return foodItemName; }
    public void setFoodItemName(String foodItemName) { this.foodItemName = foodItemName; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }
}
