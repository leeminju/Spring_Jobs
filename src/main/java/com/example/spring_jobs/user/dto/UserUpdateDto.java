package com.example.spring_jobs.user.dto;

import com.example.spring_jobs.common.annotation.Email;
import com.example.spring_jobs.common.annotation.Phone;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserUpdateDto {

    @Email @NotNull
    private String email;
    @Phone @NotNull
    private String phone;
}
