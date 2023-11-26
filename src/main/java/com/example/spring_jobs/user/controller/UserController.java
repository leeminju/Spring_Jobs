package com.example.spring_jobs.user.controller;

import com.example.spring_jobs.auth.security.UserDetailsImpl;
import com.example.spring_jobs.common.CustomResponseEntity;
import com.example.spring_jobs.common.StatusEnum;
import com.example.spring_jobs.user.dto.*;
import com.example.spring_jobs.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/users/logout")
    public ResponseEntity<CustomResponseEntity> logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        return CustomResponseEntity.toResponseEntity(StatusEnum.SUCCESS_LOGOUT);
    }


    @GetMapping("/users")
    public UserResponseDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        return userService.getUserInfo(userId);
    }

    @PatchMapping("/users")
    public ResponseEntity<CustomResponseEntity> updateUser(@Valid @RequestBody UserUpdateDto userUpdateDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        userService.updateUser(userUpdateDto, userId);
        return CustomResponseEntity.toResponseEntity(StatusEnum.SUCCESS_USER_UPDATE);
    }

    @PatchMapping("/password")
    public ResponseEntity<CustomResponseEntity> updatePassword(@Valid @RequestBody PasswordRequestDto passwordRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        userService.updatePassword(passwordRequestDto, userDetails.getUser());
        return CustomResponseEntity.toResponseEntity(StatusEnum.SUCCESS_CHANGE_PASSWORD);
    }
}
