package com.example.demo.service;

import com.example.demo.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserModel getUserByEmail(String email);
    UserModel getUserByName(String name);
    UserModel createUser(String email, String username, String password);
    UserModel getUserById(String id);
    List<UserModel> getAllUsers();
    UserModel deleteUser(String id);
}
