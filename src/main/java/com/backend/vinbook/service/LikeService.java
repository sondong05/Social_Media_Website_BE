package com.backend.vinbook.service;

import com.backend.vinbook.entity.Like;
import com.backend.vinbook.entity.Post;
import com.backend.vinbook.entity.User;
import com.backend.vinbook.repository.LikeRepository;
import com.backend.vinbook.repository.PostRepository;
import com.backend.vinbook.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public LikeService(LikeRepository likeRepository, UserRepository userRepository, PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public String toggleLike(Long postId, Long userId) {
        var existing = likeRepository.findByUserIdAndPostId(userId, postId);
        if (existing.isPresent()) {
            likeRepository.delete(existing.get());
            return "Unliked";
        } else {
            User user = userRepository.findById(userId).orElseThrow();
            Post post = postRepository.findById(postId).orElseThrow();
            Like like = new Like();
            like.setUser(user);
            like.setPost(post);
            likeRepository.save(like);
            return "Liked";
        }
    }

    public Long countLikes(Long postId) {
        return likeRepository.countByPostId(postId);
    }
}
