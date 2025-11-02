package com.example.demo.service;

import com.example.demo.dto.auth.AuthResponseDTO;

public interface AuthService {
    AuthResponseDTO register(String email, String username, String password);
    AuthResponseDTO login(String email, String password);
}
