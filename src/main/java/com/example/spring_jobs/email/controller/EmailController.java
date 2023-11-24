package com.example.spring_jobs.email.controller;

import com.example.spring_jobs.email.service.EmailService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @PostMapping("/email")
    public String emailConfirm(@RequestParam String email) throws Exception {
        System.out.println(email);
        String confirm = emailService.sendSimpleMessage(email);

        return confirm;
    }
}