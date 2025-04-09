package com.example.clickndine.service;

import com.example.clickndine.dto.CartItemDTO;
import com.example.clickndine.model.Cart;
import com.example.clickndine.model.CartItem;
import com.example.clickndine.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    /**
     * Adds an item to the cart for the given user.
     * If the cart doesn't exist, create it.
     * If the item is already in the cart, increment its quantity.
     */
    public Cart addItemToCart(Long userId, CartItemDTO cartItemDTO) {
        // 1. Retrieve the cart or create a new one
        Cart cart = findOrCreateCartByUserId(userId);

        // 2. Check if item already exists in the cart
        boolean itemExists = false;
        for (CartItem item : cart.getItems()) {
            if (item.getFoodItemId().equals(cartItemDTO.getFoodItemId())) {
                // Item found, increment quantity
                item.setQuantity(item.getQuantity() + cartItemDTO.getQuantity());
                itemExists = true;
                break;
            }
        }

        // 3. If item does not exist, create a new CartItem
        if (!itemExists) {
            CartItem newItem = new CartItem(
                    cartItemDTO.getFoodItemId(),
                    cartItemDTO.getFoodItemName(),
                    cartItemDTO.getPrice(),
                    cartItemDTO.getQuantity()
            );
            cart.getItems().add(newItem);
        }

        // 4. Save the cart
        return cartRepository.save(cart);
    }

    /**
     * Locates a cart by user ID or creates one if none exists.
     */
    private Cart findOrCreateCartByUserId(Long userId) {
        // Example logic: If you store "cart by user ID" in the Cart table,
        // you can define a custom repository method findByCustomerId(userId).
        // Or simply search by "where customerId = ?"

        // Here, we assume a custom method findByCustomerId
        Cart cart = cartRepository.findByCustomerId(userId);
        if (cart == null) {
            cart = new Cart(userId);
            cart = cartRepository.save(cart);
        }
        return cart;
    }
}
