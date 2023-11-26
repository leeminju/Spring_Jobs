package com.example.spring_jobs.post.dto;

import com.example.spring_jobs.company.entity.Company;
import lombok.Getter;

@Getter
public class PostRequestDto {
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

    public PostRequestDto(String title, String image, String contents, String job, String deadline, String career, String skill, String empType, String education) {
        this.title = title;
        this.image = image;
        this.contents = contents;
        this.job = job;
        this.deadline = deadline;
        this.career = career;
        this.skill = skill;
        this.empType = empType;
        this.education = education;
    }
}
