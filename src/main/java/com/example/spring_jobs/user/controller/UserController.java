package com.example.spring_jobs.user.controller;

import com.example.spring_jobs.common.CustomResponseEntity;
import com.example.spring_jobs.common.StatusEnum;
import com.example.spring_jobs.user.dto.LoginRequestDto;
import com.example.spring_jobs.user.dto.UserSignupRequestDto;
import com.example.spring_jobs.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

		@PostMapping("/signup/users")
	public ResponseEntity<CustomResponseEntity> signUpUser(@Valid @RequestBody UserSignupRequestDto userSignupRequestDto) {
		userService.signup(userSignupRequestDto);
		return CustomResponseEntity.toResponseEntity(StatusEnum.SUCCESS_JOIN);
	}

	@PostMapping("/signin")
	public ResponseEntity<CustomResponseEntity> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
		String token = userService.login(loginRequestDto);
		return CustomResponseEntity.toResponseEntityWithHeader(StatusEnum.SUCCESS_LOGIN, token);
	}
}
