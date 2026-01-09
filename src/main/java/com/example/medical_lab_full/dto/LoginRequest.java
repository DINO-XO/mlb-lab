package com.example.medical_lab_full.dto;

public class LoginRequest {

    private String email;
    private String password;
    private String role; // ✅ REQUIRED

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // ✅ THIS IS WHAT WAS MISSING
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
