package com.example.blog.repositary;

import com.example.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositary extends JpaRepository<User, Long> {}
