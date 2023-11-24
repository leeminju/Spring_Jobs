package com.example.spring_jobs.post.dto;

import com.example.spring_jobs.company.dto.CompanyResponseDto;
import com.example.spring_jobs.company.entity.Company;
import com.example.spring_jobs.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String image;
    private String contents;
    private String deadline;
    private String career;
    private String skill;
    private String empType;
    private String education;
    private CompanyResponseDto company;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.image = post.getImage();
        this.contents = post.getContents();
        this.deadline = post.getDeadline();
        this.career = post.getCareer();
        this.skill = post.getSkill();
        this.empType = post.getEmpType();
        this.education = post.getEducation();
        this.company = new CompanyResponseDto(post.getCompany());
    }
}
