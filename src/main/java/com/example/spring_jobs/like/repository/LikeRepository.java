package com.example.spring_jobs.like.repository;

import com.example.spring_jobs.like.entity.Like;
import com.example.spring_jobs.post.entity.Post;
import com.example.spring_jobs.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByUserAndPost(User user, Post post);
}