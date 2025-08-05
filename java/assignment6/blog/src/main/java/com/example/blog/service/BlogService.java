
package com.example.blog.service;

import com.example.blog.model.*;
import com.example.blog.repositary.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogService {

    @Autowired private UserRepositary userRepo;
    @Autowired private PostRepositary postRepo;

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public Post createPost(Long userId, Post post) {
        User author = userRepo.findById(userId).orElseThrow();
        post.setAuthor(author);
        return postRepo.save(post);
    }

    public Page<Post> getPosts(String authorName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (authorName != null) {
            return postRepo.findByAuthorName(authorName, pageable);
        }
        return postRepo.findAll(pageable);
    }

    public Optional<Post> getPostById(Long id) {
        return postRepo.findById(id);
    }
}
