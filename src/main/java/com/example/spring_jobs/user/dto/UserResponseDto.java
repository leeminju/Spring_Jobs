package com.example.spring_jobs.user.dto;

import com.example.spring_jobs.user.UserRoleEnum;
import com.example.spring_jobs.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDto {
    private String nickname;
    private String loginId;
    private String email;
    private String phone;
    private UserRoleEnum role;

    public UserResponseDto(User user) {
        this.loginId = user.getLoginId();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.role = user.getRole();
    }
}