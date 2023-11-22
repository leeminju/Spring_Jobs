package com.example.spring_jobs.user.dto;

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
public class UserSignupRequestDto {
	@LoginId
	private String loginId;
	@Password
	private String password;
	@Email
	private String email;
	@Phone
	private String phone;
}
