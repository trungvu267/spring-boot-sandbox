package com.example.demo.dto.auth;

import com.example.demo.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDTO {
    private UserModel user;
    private String access_token;
}
