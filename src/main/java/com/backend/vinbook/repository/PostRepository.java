package com.backend.vinbook.repository;

import com.backend.vinbook.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
