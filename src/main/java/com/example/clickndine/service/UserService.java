package com.example.clickndine.service;

import com.example.clickndine.dto.ForgotPasswordDTO;
import com.example.clickndine.dto.ResetPasswordDTO;
import com.example.clickndine.dto.UserLoginDTO;
import com.example.clickndine.dto.UserRegistrationDTO;
import com.example.clickndine.model.Administrator;
import com.example.clickndine.model.Customer;
import com.example.clickndine.model.Courier;
import com.example.clickndine.model.PasswordResetToken;
import com.example.clickndine.model.Restaurant;
import com.example.clickndine.model.User;
import com.example.clickndine.repository.PasswordResetTokenRepository;
import com.example.clickndine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordResetTokenRepository tokenRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Registration method
    public User registerUser(UserRegistrationDTO registrationDTO) {
        if (userRepository.findByEmail(registrationDTO.getEmail()) != null) {
            throw new RuntimeException("A user with this email already exists.");
        }
        User user;
        String type = registrationDTO.getUserType().toUpperCase();
        // Encode the password before saving
        String encodedPassword = passwordEncoder.encode(registrationDTO.getPassword());
        switch (type) {
            case "CUSTOMER":
                user = new Customer(
                        registrationDTO.getName(),
                        registrationDTO.getEmail(),
                        registrationDTO.getPhone(),
                        encodedPassword
                );
                break;
            case "COURIER":
                user = new Courier(
                        registrationDTO.getName(),
                        registrationDTO.getEmail(),
                        registrationDTO.getPhone(),
                        encodedPassword
                );
                break;
            case "ADMIN":
                user = new Administrator(
                        registrationDTO.getName(),
                        registrationDTO.getEmail(),
                        registrationDTO.getPhone(),
                        encodedPassword
                );
                break;
            case "RESTAURANT":
                Restaurant restaurant = new Restaurant(
                        registrationDTO.getName(),
                        registrationDTO.getEmail(),
                        registrationDTO.getPhone(),
                        encodedPassword
                );
                // Constructor already sets default status to "PENDING"
                user = restaurant;
                break;
            default:
                throw new RuntimeException("Invalid user type provided. Allowed types: CUSTOMER, COURIER, ADMIN, RESTAURANT.");
        }
        return userRepository.save(user);
    }

    // Login method using passwordEncoder.matches(...)
    public String loginUser(UserLoginDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.getEmail());
        if (user == null) {
            throw new RuntimeException("Invalid email or password.");
        }
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password.");
        }
        return "dummy-session-token-for-" + user.getId();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(Long id, UserRegistrationDTO updateDTO) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(updateDTO.getName());
                    user.setEmail(updateDTO.getEmail());
                    user.setPhone(updateDTO.getPhone());
                    // Re-encode updated password
                    user.setPassword(passwordEncoder.encode(updateDTO.getPassword()));
                    user.setUserType(updateDTO.getUserType());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void logoutUser(String token) {
        System.out.println("User logged out with token: " + token);
    }

    // Approve a restaurant registration
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

    // Reject a restaurant registration
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

    // Forgot password: generate a reset token
    public String forgotPassword(ForgotPasswordDTO forgotPasswordDTO) {
        String identifier = forgotPasswordDTO.getIdentifier();
        // Here, we search by email. (Extend with phone lookup if needed.)
        User user = userRepository.findByEmail(identifier);
        if(user == null) {
            throw new RuntimeException("No user found with that email or phone.");
        }
        // Generate a unique token
        String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = new PasswordResetToken(token, user.getId());
        tokenRepository.save(resetToken);
        // In production, send this token via email/SMS.
        return token;
    }

    // Reset password using the token
    public void resetPassword(ResetPasswordDTO resetPasswordDTO) {
        PasswordResetToken resetToken = tokenRepository.findByToken(resetPasswordDTO.getToken());
        if(resetToken == null || resetToken.isExpired()) {
            throw new RuntimeException("Invalid or expired reset token.");
        }
        User user = userRepository.findById(resetToken.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found for this token."));
        // Update the user's password (encode the new password)
        user.setPassword(passwordEncoder.encode(resetPasswordDTO.getNewPassword()));
        userRepository.save(user);
        // Remove the token after successful reset
        tokenRepository.delete(resetToken);
    }
}
