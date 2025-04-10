package com.example.clickndine.service;

import com.example.clickndine.dto.UserRegistrationDTO;
import com.example.clickndine.model.*;
import com.example.clickndine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Registration method (already implemented)
//    public User registerUser(UserRegistrationDTO registrationDTO) {
//        if (userRepository.findByEmail(registrationDTO.getEmail()) != null) {
//            throw new RuntimeException("A user with this email already exists.");
//        }
//        User user = new User(
//                registrationDTO.getName(),
//                registrationDTO.getEmail(),
//                registrationDTO.getPhone(),
//                registrationDTO.getPassword(),  // In production, use password hashing
//                registrationDTO.getUserType()
//        );
//        return userRepository.save(user);
//    }

    public User registerUser(UserRegistrationDTO registrationDTO) {
        if (userRepository.findByEmail(registrationDTO.getEmail()) != null) {
            throw new RuntimeException("A user with this email already exists.");
        }
        User user;
        String type = registrationDTO.getUserType().toUpperCase();
        // No password encoding: using the raw password directly.
        String rawPassword = registrationDTO.getPassword();
        switch (type) {
            case "CUSTOMER":
                user = new Customer(
                        registrationDTO.getName(),
                        registrationDTO.getEmail(),
                        registrationDTO.getPhone(),
                        rawPassword
                );
                break;
            case "COURIER":
                user = new Courier(
                        registrationDTO.getName(),
                        registrationDTO.getEmail(),
                        registrationDTO.getPhone(),
                        rawPassword
                );
                break;
            case "ADMIN":
                user = new Administrator(
                        registrationDTO.getName(),
                        registrationDTO.getEmail(),
                        registrationDTO.getPhone(),
                        rawPassword
                );
                break;
            case "RESTAURANT":
                Restaurant restaurant = new Restaurant(
                        registrationDTO.getName(),
                        registrationDTO.getEmail(),
                        registrationDTO.getPhone(),
                        rawPassword
                );
                // Constructor already sets default status to "PENDING"
                user = restaurant;
                break;
            default:
                throw new RuntimeException("Invalid user type provided. Allowed types: CUSTOMER, COURIER, ADMIN, RESTAURANT.");
        }
        return userRepository.save(user);
    }


    // Login method (already implemented)
    public String loginUser(com.example.clickndine.dto.UserLoginDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.getEmail());
        if (user == null) {
            throw new RuntimeException("Invalid email or password.");
        }
        if (!user.getPassword().equals(loginDTO.getPassword())) {
            throw new RuntimeException("Invalid email or password.");
        }
        return "dummy-session-token-for-" + user.getId();
    }

    // Retrieve a user by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Update a user account; here we use the same DTO for simplicity.
    public User updateUser(Long id, UserRegistrationDTO updateDTO) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(updateDTO.getName());
                    user.setEmail(updateDTO.getEmail());
                    user.setPhone(updateDTO.getPhone());
                    user.setPassword(updateDTO.getPassword());  // For password reset; hash in production!
                    user.setUserType(updateDTO.getUserType());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Delete a user by ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // (Optional) Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void logoutUser(String token) {
        System.out.println("User logged out with token: " + token);
    }

    // Approve a restaurant registration.
    public Restaurant approveRestaurant(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!(user instanceof Restaurant)) {
            throw new RuntimeException("User with ID " + id + " is not a restaurant.");
        }
        Restaurant restaurant = (Restaurant) user;
        restaurant.setStatus("APPROVED");
        return userRepository.save(restaurant);
    }

    // Reject a restaurant registration.
    public Restaurant rejectRestaurant(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!(user instanceof Restaurant)) {
            throw new RuntimeException("User with ID " + id + " is not a restaurant.");
        }
        Restaurant restaurant = (Restaurant) user;
        restaurant.setStatus("REJECTED");
        return userRepository.save(restaurant);
    }
}
