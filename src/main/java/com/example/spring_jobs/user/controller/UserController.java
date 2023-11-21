package com.example.spring_jobs.user.controller;

import com.example.spring_jobs.auth.jwt.JwtUtil;
import com.example.spring_jobs.user.dto.LoginRequestDto;
import com.example.spring_jobs.user.dto.UserSignupRequestDto;
import com.example.spring_jobs.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("/signup/user")
	public ResponseEntity<UserSignupRequestDto> signUpUser(@Valid @RequestBody UserSignupRequestDto userSignupRequestDto) {
		userService.signup(userSignupRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PostMapping("/signup/company")
	public ResponseEntity<UserSignupRequestDto> signUpCompany(@Valid @RequestBody UserSignupRequestDto userSignupRequestDto) {
		userService.signup(userSignupRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PostMapping("/signin")
	public ResponseEntity<LoginRequestDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
		String token = userService.login(loginRequestDto);

		return ResponseEntity
				.status(HttpStatus.OK)
				.header(JwtUtil.AUTHORIZATION_HEADER, token)
				.build();
	}
}
