package com.example.spring_jobs.post.dto;

import com.example.spring_jobs.post.entity.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponseDTO {
    private Long id;
    private String title;
    private String image;
    private String contents;
    private String deadline;
    private String career;
    private String skill;
    private String empType;
    private String education;

    public PostResponseDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.image = post.getImage();
        this.contents = post.getContents();
        this.deadline = post.getDeadline();
        this.career = post.getCareer();
        this.skill = post.getSkill();
        this.empType = post.getEmpType();
        this.education = post.getEducation();
    }
}
