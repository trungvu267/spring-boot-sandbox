package com.example.demo.controller;

import com.example.demo.dto.user.CreateUserDto;
import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("email")
    public ResponseEntity<Map<String, Object>> getUserByEmail(@RequestParam String email){
        UserModel user =  userService.getUserByEmail(email);
        if (user == null){
           return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "User not found"));
        }
        return ResponseEntity.ok(Map.of("user", user));
    }

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getUsers(){
        List<UserModel> users = userService.getAllUsers();
        return ResponseEntity.ok(Map.of(
                "message", "User created successfully",
                "users", users
        ));
    }

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody CreateUserDto req){
        String email = req.getEmail();
        String name = req.getName();

        UserModel user = userService.createUser(name, email);

        return ResponseEntity.ok(Map.of(
                "message", "User created successfully",
                "user", user
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable String id){
        UserModel user = userService.getUserById(id);
        if (user == null){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "User not found"));
        }
        return ResponseEntity.ok(Map.of(
                "message", "Get user successfully",
                "user", user
        ));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable String id){
        UserModel user = userService.deleteUser(id);
        if (user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "User not found"));
        }
        return ResponseEntity.ok(Map.of(
                "message", "Delete user successfully",
                "user", user
        ));
    }
}
