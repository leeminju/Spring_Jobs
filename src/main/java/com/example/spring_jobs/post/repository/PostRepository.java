package com.example.spring_jobs.post.repository;

import com.example.spring_jobs.company.entity.Company;
import com.example.spring_jobs.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByCompany(Company company);
}
