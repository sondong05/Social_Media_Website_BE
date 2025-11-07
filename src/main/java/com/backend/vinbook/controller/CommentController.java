package com.backend.vinbook.controller;

import com.backend.vinbook.entity.Comment;
import com.backend.vinbook.service.CommentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/post/{postId}/user/{userId}")
    public Comment addComment(@PathVariable Long postId, @PathVariable Long userId, @RequestBody Comment comment) {
        return commentService.addComment(postId, userId, comment);
    }

    @GetMapping("/post/{postId}")
    public List<Comment> getComments(@PathVariable Long postId) {
        return commentService.getCommentsByPost(postId);
    }
}
