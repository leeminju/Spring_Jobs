package com.example.spring_jobs.post;

import com.example.spring_jobs.company.entity.Company;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "post")
@NoArgsConstructor
public class Post extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String deadline;

    @Column(nullable = false)
    private String career;

    @Column(nullable = false)
    private String skill;

    @Column(name = "emp_type",nullable = false)
    private String empType;

    @Column(nullable = false)
    private String education;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
