package com.example.spring_jobs.user.dto;

import com.example.spring_jobs.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDto {
    private String loginId;
    private String email;
    private String phone;

    public UserResponseDto(User user) {
        this.loginId = user.getLoginId();
        this.email = user.getEmail();
        this.phone = user.getPhone();
    }
}