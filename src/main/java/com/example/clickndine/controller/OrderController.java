package com.example.clickndine.controller;

import com.example.clickndine.model.Order;
import com.example.clickndine.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // POST endpoint to place an order.
    // The order JSON should include order items, customer, restaurant, delivery address, etc.
    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        Order placedOrder = orderService.placeOrder(order);
        return ResponseEntity.ok(placedOrder);
    }

    // GET endpoint to fetch order details by order ID.
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable Long orderId) {
        Optional<Order> orderOpt = orderService.getOrderById(orderId);
        return orderOpt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT endpoint to cancel an order.
    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Order> cancelOrder(@PathVariable Long orderId) {
        Order cancelledOrder = orderService.cancelOrder(orderId);
        return ResponseEntity.ok(cancelledOrder);
    }
}
