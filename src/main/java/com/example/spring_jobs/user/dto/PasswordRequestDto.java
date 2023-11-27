package com.example.spring_jobs.user.dto;

import com.example.spring_jobs.common.annotation.Password;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordRequestDto {
    String currentPassword;
    @Password
    String newPassword;

    String checkPassword;
}
