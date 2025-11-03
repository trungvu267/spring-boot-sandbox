package com.example.demo.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginDTO {
    @NotEmpty(message = "Email is not empty")
    @Email(message = "Email is not valid")
    private String email;

    @NotEmpty(message = "Password is not empty")
    private String password;
}
