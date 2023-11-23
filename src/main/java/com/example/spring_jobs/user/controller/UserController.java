package com.example.spring_jobs.user.controller;

import com.example.spring_jobs.common.CustomResponseEntity;
import com.example.spring_jobs.common.StatusEnum;
import com.example.spring_jobs.company.dto.CompanyUpdateDto;
import com.example.spring_jobs.user.dto.LoginRequestDto;
import com.example.spring_jobs.user.dto.UserResponseDto;
import com.example.spring_jobs.user.dto.UserSignupRequestDto;
import com.example.spring_jobs.user.dto.UserUpdateDto;
import com.example.spring_jobs.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("/signup/user")
	public ResponseEntity<CustomResponseEntity> signUpUser(@Valid @RequestBody UserSignupRequestDto userSignupRequestDto) {
		userService.signup(userSignupRequestDto);
		return CustomResponseEntity.toResponseEntity(StatusEnum.SUCCESS_JOIN);
	}

	@PostMapping("/signin")
	public ResponseEntity<CustomResponseEntity> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
		String token = userService.login(loginRequestDto);
		return CustomResponseEntity.toResponseEntityWithHeader(StatusEnum.SUCCESS_LOGIN, token);
	}

    @GetMapping("user-info")
    public UserResponseDto getCompanyInfo(@RequestHeader("Authorization") String token) {
        return userService.getUserInfo(token);
    }

	@PatchMapping("user-info")
	public ResponseEntity<CustomResponseEntity> updateCompany(@Valid @RequestBody UserUpdateDto userUpdateDto, @RequestHeader("Authorization") String token) {
		userService.updateUser(userUpdateDto, token);
		return CustomResponseEntity.toResponseEntity(StatusEnum.SUCCESS_USER_UPDATE);
	}

}
