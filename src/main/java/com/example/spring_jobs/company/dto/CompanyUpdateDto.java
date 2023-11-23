package com.example.spring_jobs.company.dto;

import com.example.spring_jobs.common.annotation.Email;
import com.example.spring_jobs.common.annotation.Phone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CompanyUpdateDto {
    @NotBlank
    private String nickname;
    @Email @NotNull
    private String email;
    @Phone @NotNull
    private String phone;
    @NotNull
    private String companyName;
    @NotNull
    private String location;
    @NotNull
    private String industry;
}
