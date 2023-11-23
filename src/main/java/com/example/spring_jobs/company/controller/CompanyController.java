package com.example.spring_jobs.company.controller;

import com.example.spring_jobs.common.CustomResponseEntity;
import com.example.spring_jobs.common.StatusEnum;
import com.example.spring_jobs.company.dto.CompanySignupRequestDto;
import com.example.spring_jobs.company.service.CompanyService;
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
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping("/signup/companies")
	public ResponseEntity<CustomResponseEntity> signUpCompany(@Valid @RequestBody CompanySignupRequestDto companySignupRequestDto) {
		companyService.signup(companySignupRequestDto);
		return CustomResponseEntity.toResponseEntity(StatusEnum.SUCCESS_JOIN);
	}
}
