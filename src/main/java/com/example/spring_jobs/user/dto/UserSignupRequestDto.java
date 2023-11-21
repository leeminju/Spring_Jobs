package com.example.spring_jobs.user.dto;

import com.example.spring_jobs.user.UserRoleEnum;
import com.example.spring_jobs.user.annotation.Password;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupRequestDto {
	private String loginId;
	@Password
	private String password;
	private String email;
	private String phone;
}
