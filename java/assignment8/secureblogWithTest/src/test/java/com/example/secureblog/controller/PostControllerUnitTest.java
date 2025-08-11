package com.example.secureblog.controller;

import com.example.secureblog.model.Post;
import com.example.secureblog.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostControllerUnitTest {

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    private Post post1;

    @BeforeEach
    void setup() {
        post1 = new Post("Title 1", "Content 1");
        post1.setId(1L);
    }

    @Test
    void testGetAllPosts() {
        when(postService.getAllPosts()).thenReturn(List.of(post1));

        ResponseEntity<?> response = postController.getAllPosts();

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        List<?> posts = (List<?>) response.getBody();
        assertThat(posts).isNotNull();
        assertThat(posts).hasSize(1);
    }

    @Test
    void testCreatePost() {
        // Arrange
        when(postService.createPost(any(Post.class))).thenReturn(post1);
        Post postToCreate = new Post("Title 1", "Content 1");

        // Act
        ResponseEntity<Map<String, Object>> response = postController.createPost(postToCreate);

        // Assert
        assertThat(response.getStatusCode().value()).isEqualTo(200);

        // --- FINAL FIX ---
        // The test now checks for the exact keys and values returned by the controller.
        Map<String, Object> responseMap = response.getBody();

        assertThat(responseMap).isNotNull();
        assertThat(responseMap).hasSize(3); // Expecting 3 key-value pairs
        assertThat(responseMap.get("message")).isEqualTo("Post created");
        assertThat(responseMap.get("title")).isEqualTo("Title 1");
        assertThat(responseMap.get("content")).isEqualTo("Content 1");
    }
}