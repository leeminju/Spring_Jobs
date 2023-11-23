package com.example.spring_jobs.post.dto;

import com.example.spring_jobs.company.entity.Company;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDTO {
    private Company company;
    private String title;
    private String image;
    private String contents;
    private String job;
    private String deadline;
    private String career;
    private String skill;
    private String empType;
    private String education;
}
