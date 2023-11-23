package com.example.spring_jobs.post.repository;

import com.example.spring_jobs.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
