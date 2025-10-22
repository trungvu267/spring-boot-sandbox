package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@SQLDelete(sql = "UPDATE users SET deleted_at = CURRENT_TIMESTAMP WHERE id=?")
//@Where(clause = "deleted_at IS NULL")
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "CHAR(36)")
    private String id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private LocalDateTime deleted_at;

    public UserModel(){}
    public UserModel(String name, String email){
        this.name = name;
        this.email = email;
    }

}
