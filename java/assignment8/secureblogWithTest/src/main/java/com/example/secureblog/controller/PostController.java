package com.example.secureblog.controller;

import com.example.secureblog.model.Post;
import com.example.secureblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Map<String, Object>> createPost(@RequestBody Post post) {
        Post savedPost = postService.createPost(post);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Post created");
        response.put("title", savedPost.getTitle());
        response.put("content", savedPost.getContent());

        return ResponseEntity.ok(response);
    }
}
