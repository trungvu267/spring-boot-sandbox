package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.User;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @JsonIgnore
    @Column
    private String password;

    @Column
    private LocalDateTime deleted_at;

    public UserModel(String email, String name, String password){
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
