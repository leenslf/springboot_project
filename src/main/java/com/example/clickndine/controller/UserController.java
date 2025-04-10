package com.example.clickndine.controller;

import com.example.clickndine.dto.UserLoginDTO;
import com.example.clickndine.dto.UserRegistrationDTO;
import com.example.clickndine.model.User;
import com.example.clickndine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Existing registration endpoint
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserRegistrationDTO registrationDTO) {
        User createdUser = userService.registerUser(registrationDTO);
        return ResponseEntity.ok(createdUser);
    }

    // Existing login endpoint
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody UserLoginDTO loginDTO) {
        String sessionToken = userService.loginUser(loginDTO);
        return ResponseEntity.ok(sessionToken);
    }

    // ----------------------------
    // New endpoints for account management
    // ----------------------------

    // View Account Details: GET /api/users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<User> viewAccount(@PathVariable Long id) {
        Optional<User> userOpt = userService.getUserById(id);
        return userOpt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update Account Details (e.g., reset password): PUT /api/users/{id}
    @PutMapping("/{id}")
    public ResponseEntity<User> updateAccount(@PathVariable Long id,
                                              @Valid @RequestBody UserRegistrationDTO updateDTO) {
        User updatedUser = userService.updateUser(id, updateDTO);
        return ResponseEntity.ok(updatedUser);
    }

    // Delete Account: DELETE /api/users/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Logout API endpoint
    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(@RequestHeader("Authorization") String token) {
        // Optionally, you can add logic to remove the token from a store.
        userService.logoutUser(token);
        return ResponseEntity.ok("Logged out successfully");
    }
}
