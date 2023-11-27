package com.example.spring_jobs.comment.repository;

import com.example.spring_jobs.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByUserIdAndPostId(Long userId, Long postId);
}
