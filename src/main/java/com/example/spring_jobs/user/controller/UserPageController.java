package com.example.spring_jobs.user.controller;

import com.example.spring_jobs.auth.security.UserDetailsImpl;
import com.example.spring_jobs.user.dto.UserResponseDto;
import com.example.spring_jobs.user.dto.UserUpdateDto;
import com.example.spring_jobs.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserPageController {

    private final UserService userService;

    @GetMapping
    public String user(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        UserResponseDto user = getUser(userDetails);
        model.addAttribute("user", user);
        return "user/userMypage";
    }

    @GetMapping("/edit")
    public String updateUserForm(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        UserResponseDto user = getUser(userDetails);
        model.addAttribute("user", user);
        return "user/userUpdateForm";
    }

    @PostMapping("/edit")
    public String updateUser(@Valid @ModelAttribute UserUpdateDto userUpdateDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        userService.updateUser(userUpdateDto,userId);
        return "redirect:/user";
    }

    private UserResponseDto getUser(UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        UserResponseDto user = userService.getUserInfo(userId);
        return user;
    }
}
