package com.example.clickndine.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "password_reset_tokens")
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private Long userId; // The user this token is for

    private LocalDateTime expiryDate;

    public static final long EXPIRATION_MINUTES = 30;

    public PasswordResetToken() {}

    public PasswordResetToken(String token, Long userId) {
        this.token = token;
        this.userId = userId;
        this.expiryDate = LocalDateTime.now().plusMinutes(EXPIRATION_MINUTES);
    }

    // Getters and setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) { this.id = id; }

    public String getToken() {
        return token;
    }
    public void setToken(String token) { this.token = token; }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) { this.userId = userId; }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(LocalDateTime expiryDate) { this.expiryDate = expiryDate; }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiryDate);
    }
}
