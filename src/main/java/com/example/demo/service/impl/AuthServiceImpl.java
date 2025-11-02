package com.example.demo.service.impl;

import com.example.demo.auth.JwtService;
import com.example.demo.dto.auth.AuthResponseDTO;
import com.example.demo.model.UserModel;
import com.example.demo.service.AuthService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    public UserService userService;
    @Autowired
    public PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;


    @Override
    public AuthResponseDTO register(String email, String username, String password){
        UserModel exist_user = userService.getUserByEmail(email);
        if(exist_user != null){
            throw new RuntimeException("User already exist");
        }
        UserModel user = userService.createUser(email, username, password);
        String access_token = jwtService.generateToken(user.getEmail());

        return new AuthResponseDTO(user, access_token);
    }

    @Override
    public AuthResponseDTO login(String email, String password){
        UserModel exist_user = userService.getUserByEmail(email);
        if(exist_user == null){
            throw new RuntimeException("User already not exist");
        }

        if(!passwordEncoder.matches(password, exist_user.getPassword())){
            throw new RuntimeException("Not Authentication");

        }
        String access_token = jwtService.generateToken(exist_user.getEmail());
            return new AuthResponseDTO(exist_user, access_token);
    }

}
