package com.example.spring_jobs.user.dto;

import com.example.spring_jobs.user.UserRoleEnum;
import com.example.spring_jobs.user.annotation.Email;
import com.example.spring_jobs.user.annotation.LoginId;
import com.example.spring_jobs.user.annotation.Password;
import com.example.spring_jobs.user.annotation.Phone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompanySignupRequestDto {
	@LoginId
	private String loginId;
	@Password
	private String password;
	@Email
	private String email;
	private UserRoleEnum role;
	@Phone
	private String phone;
	private String companyName;
	private String location;
	private String industry;
}
