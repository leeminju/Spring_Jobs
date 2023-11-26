package com.example.spring_jobs.company.controller;

import com.example.spring_jobs.auth.security.UserDetailsImpl;
import com.example.spring_jobs.company.dto.CompanyResponseDto;
import com.example.spring_jobs.company.dto.CompanyUpdateDto;
import com.example.spring_jobs.company.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyPageController {


    private final CompanyService companyService;

    @GetMapping
    public String company(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        CompanyResponseDto company = getCompany(userDetails);
        model.addAttribute("company", company);
        return "company/companyMypage";
    }

    @GetMapping("/edit")
    public String updateCompanyForm(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        CompanyResponseDto company = getCompany(userDetails);
        model.addAttribute("company", company);
        return "company/companyUpdateForm";
    }

    @PostMapping("/edit")
    public String updateCompany(@Valid @ModelAttribute CompanyUpdateDto companyUpdateDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long companyId = userDetails.getUser().getCompany().getId();
        companyService.updateCompany(companyUpdateDto, companyId);
        return "redirect:/company";
    }

    private CompanyResponseDto getCompany(UserDetailsImpl userDetails) {
        Long companyId = userDetails.getUser().getCompany().getId();
        CompanyResponseDto company = companyService.getCompanyInfo(companyId);
        return company;
    }
}
