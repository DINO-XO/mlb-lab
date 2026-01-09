package com.example.medical_lab_full.dto;

import com.example.medical_lab_full.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponse {
    private Long id;
    private String name;
    private String email;
    private Role role;
}
