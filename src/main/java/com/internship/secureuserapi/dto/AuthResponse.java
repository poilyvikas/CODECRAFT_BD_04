package com.internship.secureuserapi.dto;

public class AuthResponse {
    public String token;
    public String role;

    public AuthResponse() {}

    public AuthResponse(String token, String role) {
        this.token = token;
        this.role = role;
    }
}
