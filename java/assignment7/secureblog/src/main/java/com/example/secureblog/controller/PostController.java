package com.example.secureblog.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/posts")
public class PostController {

    @GetMapping
    public ResponseEntity<?> getAllPosts() {
        return ResponseEntity.ok(List.of(
                Map.of("title", "First Post", "content", "Hello World"),
                Map.of("title", "Second Post", "content", "Secure Blog")
        ));
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> createPost(@RequestBody Map<String, String> postData) {
        return ResponseEntity.ok(Map.of(
                "message", "Post created",
                "title", postData.get("title"),
                "content", postData.get("content")
        ));
    }
}
