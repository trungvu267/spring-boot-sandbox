package com.example.demo.service.impl;

import com.example.demo.exception.AppException;
import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Override
    public UserModel getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public UserModel getUserByName(String name){
        return userRepository.findByName(name);
    }

    @Override
    public UserModel createUser(String email, String username, String password){
        String hash_password = passwordEncoder.encode(password);
        UserModel new_user = new UserModel(email, username, hash_password);
        return userRepository.save(new_user);
    }

    @Override
    public UserModel getUserById(String id){
        return userRepository.findById(id)
                .orElseThrow(() -> new AppException(401,"User not found"));
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
