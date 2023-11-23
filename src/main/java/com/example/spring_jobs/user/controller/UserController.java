package com.example.spring_jobs.user.controller;

import com.example.spring_jobs.auth.security.UserDetailsImpl;
import com.example.spring_jobs.common.CustomResponseEntity;
import com.example.spring_jobs.common.StatusEnum;
import com.example.spring_jobs.company.dto.CompanyUpdateDto;
import com.example.spring_jobs.user.dto.LoginRequestDto;
import com.example.spring_jobs.user.dto.UserResponseDto;
import com.example.spring_jobs.user.dto.PasswordRequestDto;
import com.example.spring_jobs.user.dto.UserSignupRequestDto;
import com.example.spring_jobs.user.dto.UserUpdateDto;
import com.example.spring_jobs.common.CustomResponseEntity;
import com.example.spring_jobs.common.StatusEnum;
import com.example.spring_jobs.user.dto.LoginRequestDto;
import com.example.spring_jobs.user.dto.UserSignupRequestDto;
import com.example.spring_jobs.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("/users/signup")
	public ResponseEntity<CustomResponseEntity> signUpUser(@Valid @RequestBody UserSignupRequestDto userSignupRequestDto) {
		userService.signup(userSignupRequestDto);
		return CustomResponseEntity.toResponseEntity(StatusEnum.SUCCESS_JOIN);
	}

	@PostMapping("/signin")
	public ResponseEntity<CustomResponseEntity> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
		String token = userService.login(loginRequestDto);
		return CustomResponseEntity.toResponseEntityWithHeader(StatusEnum.SUCCESS_LOGIN, token);
	}

    @GetMapping("/users")
    public UserResponseDto getCompanyInfo(@RequestHeader("Authorization") String token) {
        return userService.getUserInfo(token);
    }

	@PatchMapping("/users")
	public ResponseEntity<CustomResponseEntity> updateCompany(@Valid @RequestBody UserUpdateDto userUpdateDto, @RequestHeader("Authorization") String token) {
		userService.updateUser(userUpdateDto, token);
		return CustomResponseEntity.toResponseEntity(StatusEnum.SUCCESS_USER_UPDATE);
	}

	@PatchMapping("/password")
	public ResponseEntity<CustomResponseEntity> updatePassword(@Valid @RequestBody PasswordRequestDto passwordRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
		userService.updatePassword(passwordRequestDto,userDetails.getUser());
		return CustomResponseEntity.toResponseEntity(StatusEnum.SUCCESS_CHANGE_PASSWORD);
	}
}
