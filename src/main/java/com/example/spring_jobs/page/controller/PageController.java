package com.example.spring_jobs.page.controller;

import com.example.spring_jobs.auth.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PageController {
    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login-page")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup-page")
    public String signupPage() {
        return "signup";
    }

    @GetMapping("/post-page")
    public String postPage() {
        return "post";
    }

    @GetMapping("/password")
    public String updatePasswordForm() {
        return "passwordForm";
    }

}