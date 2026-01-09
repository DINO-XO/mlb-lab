package com.example.medical_lab_full.service;

import com.example.medical_lab_full.dto.LoginRequest;
import com.example.medical_lab_full.dto.RegisterRequest;
import com.example.medical_lab_full.entity.Role;
import com.example.medical_lab_full.entity.Technician;
import com.example.medical_lab_full.entity.User;
import com.example.medical_lab_full.repository.TechnicianRepository;
import com.example.medical_lab_full.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final TechnicianRepository technicianRepository;

    public AuthService(UserRepository userRepository,
                       TechnicianRepository technicianRepository) {
        this.userRepository = userRepository;
        this.technicianRepository = technicianRepository;
    }

    // ================= REGISTER (PATIENT ONLY) =================
    public User register(RegisterRequest request) {

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(Role.PATIENT);

        return userRepository.save(user);
    }

    // ================= LOGIN (ROLE AWARE) =================
    public Object login(LoginRequest request) {

        // 🔐 PATIENT LOGIN
        if (Role.PATIENT.name().equalsIgnoreCase(request.getRole())) {
            return userRepository.findByEmail(request.getEmail())
                    .filter(u -> u.getPassword().equals(request.getPassword()))
                    .orElseThrow(() ->
                            new RuntimeException("Invalid patient credentials"));
        }

        // 🔐 TECHNICIAN LOGIN
        if (Role.TECHNICIAN.name().equalsIgnoreCase(request.getRole())) {
            return technicianRepository.findByEmail(request.getEmail())
                    .filter(t -> t.getPassword().equals(request.getPassword()))
                    .orElseThrow(() ->
                            new RuntimeException("Invalid technician credentials"));
        }

        throw new RuntimeException("Invalid role");
    }
}
