package com.example.spring_jobs.page.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PageController {
    @GetMapping("/")
    public String home() {
        return "index";
    }


    @GetMapping("/page/login-page")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/page/signup-page")
    public String signupPage() {
        return "signup";
    }

    @GetMapping("/page/my-page")
    public String myPage() {
        return "mypage";
    }
}