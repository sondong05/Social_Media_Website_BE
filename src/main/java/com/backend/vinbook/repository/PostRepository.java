package com.backend.vinbook.repository;

import com.backend.vinbook.entity.Post;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
	List<Post> findByUserId(Long userId);
}
