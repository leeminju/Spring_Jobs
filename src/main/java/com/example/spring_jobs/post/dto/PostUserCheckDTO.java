package com.example.spring_jobs.post.dto;

import com.example.spring_jobs.user.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostUserCheckDTO {
    private String loginId;

    public PostUserCheckDTO(User user) {
        this.loginId = user.getLoginId();
    }

}
