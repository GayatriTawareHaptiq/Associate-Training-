package com.example.blog.repositary;

import com.example.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositary extends JpaRepository<Post, Long> {
    Page<Post> findByAuthorName(String authorName, Pageable pageable);
}
