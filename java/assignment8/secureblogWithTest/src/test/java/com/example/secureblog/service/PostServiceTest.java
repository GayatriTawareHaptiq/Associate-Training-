package com.example.secureblog.service;

import com.example.secureblog.model.Post;
import com.example.secureblog.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for PostService using JUnit 5 + Mockito
 */
@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    private Post post1;
    private Post post2;

    @BeforeEach
    void setUp() {
        post1 = new Post("Title 1", "Content 1");
        post1.setId(1L);
        post2 = new Post("Title 2", "Content 2");
        post2.setId(2L);
    }

    @Test
    void testGetAllPosts() {
        when(postRepository.findAll()).thenReturn(Arrays.asList(post1, post2));

        List<Post> posts = postService.getAllPosts();

        assertThat(posts).hasSize(2);
        assertThat(posts.get(0).getTitle()).isEqualTo("Title 1");
        verify(postRepository, times(1)).findAll();
    }

    @Test
    void testCreatePost() {
        when(postRepository.save(any(Post.class))).thenReturn(post1);

        Post savedPost = postService.createPost(post1);

        assertThat(savedPost.getTitle()).isEqualTo("Title 1");
        verify(postRepository, times(1)).save(post1);
    }

    @Test
    void testDeletePost() {
        Long postId = 1L;
        // The delete method returns void, so we just verify it was called.
        // No 'when' needed since the method doesn't return anything.
        postService.deletePost(postId);

        // Verify that the repository's deleteById method was called exactly once with the correct ID.
        verify(postRepository, times(1)).deleteById(postId);
    }

    // --- CORRECTED TESTS for getPostById ---

    @Test
    void testGetPostById_WhenFound() {
        // Arrange: When the repository's findById is called with 1L, return an Optional containing post1.
        when(postRepository.findById(1L)).thenReturn(Optional.of(post1));

        // Act: Call the service method.
        Optional<Post> result = postService.getPostById(1L);

        // Assert: The result should be present and contain the correct post.
        assertThat(result).isPresent();
        assertThat(result.get().getTitle()).isEqualTo("Title 1");
        verify(postRepository, times(1)).findById(1L);
    }

    @Test
    void testGetPostById_WhenNotFound() {
        // Arrange: When the repository is called for an ID that doesn't exist, it returns an empty Optional.
        when(postRepository.findById(99L)).thenReturn(Optional.empty());

        // Act: Call the service method with the non-existent ID.
        Optional<Post> result = postService.getPostById(99L);

        // Assert: The result should be an empty Optional.
        assertThat(result).isEmpty();
        verify(postRepository, times(1)).findById(99L);
    }
}