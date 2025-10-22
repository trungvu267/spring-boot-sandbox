package com.example.demo.repository;

import com.example.demo.model.UserModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByEmail(String email);
    UserModel findByName(String name);
    Optional<UserModel> findById(String id);

    @Query("select u from UserModel u where u.deleted_at IS NULL")
    List<UserModel> findAllUser();
}
