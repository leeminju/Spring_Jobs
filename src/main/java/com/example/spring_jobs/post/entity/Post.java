package com.example.spring_jobs.post.entity;

import com.example.spring_jobs.company.entity.Company;
import com.example.spring_jobs.post.Timestamped;
import com.example.spring_jobs.post.dto.PostRequestDto;
import com.example.spring_jobs.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "post")
@NoArgsConstructor
@AllArgsConstructor
public class Post extends Timestamped {
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
    private String job;

    @Column(nullable = false)
    private String deadline;

    @Column(nullable = false)
    private String career;

    @Column(nullable = false)
    private String skill;

    @Column(name = "emp_type", nullable = false)
    private String empType;

    @Column(nullable = false)
    private String education;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    public Post(PostRequestDto postRequestDTO, User user) {
        this.title = postRequestDTO.getTitle();
        this.image = postRequestDTO.getImage();
        this.contents = postRequestDTO.getContents();
        this.job = postRequestDTO.getJob();
        this.deadline = postRequestDTO.getDeadline();
        this.career = postRequestDTO.getCareer();
        this.skill = postRequestDTO.getSkill();
        this.empType = postRequestDTO.getEmpType();
        this.education = postRequestDTO.getEducation();
        this.company = user.getCompany();
    }
}
