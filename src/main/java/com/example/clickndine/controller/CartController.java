package com.example.clickndine.controller;

import com.example.clickndine.dto.CartItemDTO;
import com.example.clickndine.model.Cart;
import com.example.clickndine.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cart")
@Validated
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    /**
     * Endpoint to add an item to the user's cart.
     *
     * If the user is not logged in, you can return an error (401) or redirect as needed.
     *
     * Example request:
     * POST /api/cart/1/add
     * {
     *   "foodItemId": 101,
     *   "foodItemName": "Pizza Margherita",
     *   "price": 9.99,
     *   "quantity": 1
     * }
     */
    @PostMapping("/{userId}/add")
    public ResponseEntity<Cart> addItemToCart(
            @PathVariable Long userId,
            @Valid @RequestBody CartItemDTO cartItemDTO) {

        // If userId is invalid, handle it
        if (userId == null || userId <= 0) {
            // For demonstration, we just throw an exception
            // In a real app, you might return 401 or handle session-based authentication
            throw new RuntimeException("User not logged in or invalid user ID");
        }

        Cart updatedCart = cartService.addItemToCart(userId, cartItemDTO);
        return ResponseEntity.ok(updatedCart);
    }

    // You can add other endpoints like viewCart, updateCartItem, removeItem, etc.
}
