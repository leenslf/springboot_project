package com.example.clickndine.dto;

import jakarta.validation.constraints.NotBlank;

public class ForgotPasswordDTO {

    @NotBlank(message = "Email or phone is required")
    private String identifier; // Can be email (or phone)

    public String getIdentifier() {
        return identifier;
    }
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
