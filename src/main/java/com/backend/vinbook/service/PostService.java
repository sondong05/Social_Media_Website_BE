package com.backend.vinbook.service;

import com.backend.vinbook.entity.Post;
import com.backend.vinbook.entity.User;
import com.backend.vinbook.repository.PostRepository;
import com.backend.vinbook.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Post createPost(Long userId, Post post) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        post.setUser(user);
        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
