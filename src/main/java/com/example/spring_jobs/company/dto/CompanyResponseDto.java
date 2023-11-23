package com.example.spring_jobs.company.dto;

import com.example.spring_jobs.company.entity.Company;
import com.example.spring_jobs.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CompanyResponseDto {

    private String email;
    private String phone;
    private String companyName;
    private String location;
    private String industry;


    public CompanyResponseDto(Company company) {
        User user = company.getUser();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.companyName = company.getCompanyName();
        this.location = company.getLocation();
        this.industry = company.getIndustry();
    }
}