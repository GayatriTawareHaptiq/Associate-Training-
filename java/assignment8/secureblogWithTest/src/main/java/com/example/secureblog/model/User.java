package com.example.secureblog.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users") // "user" is often a reserved keyword in SQL
@Data // Lombok: auto-generates getters, setters, toString, etc.
@NoArgsConstructor // Lombok: creates a no-argument constructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role; // e.g., "ROLE_USER" or "ROLE_ADMIN"

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}