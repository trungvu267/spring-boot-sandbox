package com.example.demo.service.impl;

import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserModel getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public UserModel getUserByName(String name){
        return userRepository.findByName(name);
    }

    @Override
    public UserModel createUser(String name, String email){
        UserModel new_user = new UserModel(name, email);
        return userRepository.save(new_user);
    }

    @Override
    public UserModel getUserById(String id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<UserModel> getAllUsers(){
        return userRepository.findAllUser();
    }

    @Override
    @Transactional
    public UserModel deleteUser(String id) {
        UserModel user = userRepository.findById(id).orElse(null);

        if (user == null) {
            return null;
        }

        userRepository.delete(user);
        return user;
    }

}
