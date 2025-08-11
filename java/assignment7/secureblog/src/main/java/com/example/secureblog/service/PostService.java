package com.example.secureblog.service;

import com.example.secureblog.model.Post;
import com.example.secureblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    // This is the method the controller is looking for.
    // Ensure the name is spelled correctly.
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public Object getPostById(long l) {

        return null;
    }
}