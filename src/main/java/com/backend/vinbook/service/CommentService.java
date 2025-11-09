package com.backend.vinbook.service;

import com.backend.vinbook.entity.Comment;
import com.backend.vinbook.entity.Post;
import com.backend.vinbook.entity.User;
import com.backend.vinbook.repository.CommentRepository;
import com.backend.vinbook.repository.PostRepository;
import com.backend.vinbook.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Comment addComment(Long postId, Long userId, Comment comment) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        comment.setPost(post);
        comment.setUser(user);
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByPost(Long postId) {
        return commentRepository.findByPostId(postId);
    }
}
