package com.example.secureblog.repository;

import com.example.secureblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Spring Data JPA automatically creates the query from the method name
    Optional<User> findByUsername(String username);
}