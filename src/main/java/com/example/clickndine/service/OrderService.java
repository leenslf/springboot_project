package com.example.clickndine.service;

import com.example.clickndine.model.Order;
import com.example.clickndine.model.OrderItem;
import com.example.clickndine.model.OrderStatus;
import com.example.clickndine.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Places an order by calculating the total and saving to the database.
    public Order placeOrder(Order order) {
        // Calculate the total from all order items (price * quantity)
        double total = order.getOrderItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        order.setTotalAmount(total);
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.PLACED);
        return orderRepository.save(order);
    }

    // Retrieves order details by order ID.
    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    // Cancels an order if it has not been processed (only allow cancellation if still PLACED).
    public Order cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        if (order.getStatus() != OrderStatus.PLACED) {
            throw new RuntimeException("Order cannot be cancelled at this stage");
        }
        order.setStatus(OrderStatus.CANCELLED);
        return orderRepository.save(order);
    }
}
