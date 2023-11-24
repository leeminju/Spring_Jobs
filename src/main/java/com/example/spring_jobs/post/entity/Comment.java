package com.example.spring_jobs.post.entity;

import com.example.spring_jobs.company.entity.Company;
import com.example.spring_jobs.post.Timestamped;
import com.example.spring_jobs.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "comment")
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends Timestamped {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(nullable = false)
    private String content;

}
