package com.example.demo.controller;

import com.example.demo.auth.JwtService;
import com.example.demo.dto.auth.LoginDTO;
import com.example.demo.dto.auth.RegisterDTO;
import com.example.demo.dto.auth.AuthResponseDTO;
import com.example.demo.model.UserModel;
import com.example.demo.service.AuthService;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;

    @PostMapping("register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterDTO registerDTO){
        String password = registerDTO.getPassword();
        String email = registerDTO.getEmail();
        String username = registerDTO.getUsername();

        AuthResponseDTO res = authService.register(email,username, password);

        return ResponseEntity.ok(res);
    }
    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid LoginDTO loginDTO){
        String email = loginDTO.getEmail();
        String password = loginDTO.getPassword();

        AuthResponseDTO res = authService.login(email, password);

        return ResponseEntity.ok(res);
    }

    @GetMapping("me")
    public ResponseEntity<UserModel> getMe(@AuthenticationPrincipal UserDetails userDetails){
          String email = userDetails.getUsername();
          UserModel user = userService.getUserByEmail(email);
          return ResponseEntity.ok(user);
    }
}
