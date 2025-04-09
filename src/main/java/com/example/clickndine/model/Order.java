package com.example.clickndine.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Assuming a Customer entity exists (see note about inheritance)
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // Restaurant that received the order
    @ManyToOne(optional = false)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    // Courier is optional if not assigned immediately
    @ManyToOne
    @JoinColumn(name = "courier_id")
    private Courier courier;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    private double totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    // Delivery address, using an embedded Address class.
    @Embedded
    private Address deliveryAddress;

    public Order() {
        this.createdAt = LocalDateTime.now();
        this.status = OrderStatus.PLACED;
    }

    public Order(Customer customer, Restaurant restaurant, Address deliveryAddress) {
        this();
        this.customer = customer;
        this.restaurant = restaurant;
        this.deliveryAddress = deliveryAddress;
    }

    // Getters and Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public Restaurant getRestaurant() { return restaurant; }
    public void setRestaurant(Restaurant restaurant) { this.restaurant = restaurant; }

    public Courier getCourier() { return courier; }
    public void setCourier(Courier courier) { this.courier = courier; }

    public List<OrderItem> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItem> orderItems) { this.orderItems = orderItems; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Address getDeliveryAddress() { return deliveryAddress; }
    public void setDeliveryAddress(Address deliveryAddress) { this.deliveryAddress = deliveryAddress; }

    // Convenience method to add order items
    public void addOrderItem(OrderItem item) {
        this.orderItems.add(item);
        item.setOrder(this);
    }
}
