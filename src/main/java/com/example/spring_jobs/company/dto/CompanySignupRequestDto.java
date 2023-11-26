package com.example.spring_jobs.company.dto;

import com.example.spring_jobs.common.annotation.Email;
import com.example.spring_jobs.common.annotation.LoginId;
import com.example.spring_jobs.common.annotation.Password;
import com.example.spring_jobs.common.annotation.Phone;
import com.example.spring_jobs.user.UserRoleEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompanySignupRequestDto {
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
	private String companyName;
	private String location;
	private String industry;
	private boolean confirmed;
}
