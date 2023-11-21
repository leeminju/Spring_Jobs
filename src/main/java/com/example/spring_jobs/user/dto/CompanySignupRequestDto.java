package com.example.spring_jobs.user.dto;

import com.example.spring_jobs.user.UserRoleEnum;
import com.example.spring_jobs.user.annotation.Password;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompanySignupRequestDto {
	private String loginId;
	@Password
	private String password;
	private String email;
	private UserRoleEnum role;
	private String phone;
	private String companyName;
	private String location;
	private String industry;
}
