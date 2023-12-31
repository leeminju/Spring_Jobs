package com.example.spring_jobs.user.dto;

import com.example.spring_jobs.common.annotation.Email;
import com.example.spring_jobs.common.annotation.LoginId;
import com.example.spring_jobs.common.annotation.Password;
import com.example.spring_jobs.common.annotation.Phone;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupRequestDto {
	@LoginId
	private String loginId;
	@NotBlank
	private String nickname;
	@Password
	private String password;
	@Email
	private String email;
	@Phone
	private String phone;
	private boolean confirmed;
}
