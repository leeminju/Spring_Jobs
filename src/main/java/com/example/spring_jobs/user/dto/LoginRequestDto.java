package com.example.spring_jobs.user.dto;

import com.example.spring_jobs.user.annotation.Password;
import com.example.spring_jobs.user.annotation.Username;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {
	private String loginId;
	@Password
	private String password;
}
