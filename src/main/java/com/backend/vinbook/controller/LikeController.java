package com.backend.vinbook.controller;

import com.backend.vinbook.service.LikeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
public class LikeController {
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/post/{postId}/user/{userId}")
    public String toggleLike(@PathVariable Long postId, @PathVariable Long userId) {
        return likeService.toggleLike(postId, userId);
    }

    @GetMapping("/post/{postId}/count")
    public Long countLikes(@PathVariable Long postId) {
        return likeService.countLikes(postId);
    }
}
