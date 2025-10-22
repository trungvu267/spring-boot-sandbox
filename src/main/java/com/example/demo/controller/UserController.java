package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("users")
public class UserController {
    @GetMapping
    public List<Map<String, String>> getAllUsers(){
        return List.of(
                Map.of("id", "1", "name", "Alice")
        );
    }
}
