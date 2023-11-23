package com.example.spring_jobs.company.controller;

import com.example.spring_jobs.common.CustomResponseEntity;
import com.example.spring_jobs.common.StatusEnum;
import com.example.spring_jobs.company.dto.CompanyResponseDto;
import com.example.spring_jobs.company.dto.CompanySignupRequestDto;
import com.example.spring_jobs.company.dto.CompanyUpdateDto;
import com.example.spring_jobs.company.dto.CompanySignupRequestDto;
import com.example.spring_jobs.company.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping("/companies/signup")
	public ResponseEntity<CustomResponseEntity> signUpCompany(@Valid @RequestBody CompanySignupRequestDto companySignupRequestDto) {
		companyService.signup(companySignupRequestDto);
		return CustomResponseEntity.toResponseEntity(StatusEnum.SUCCESS_JOIN);
	}

	@GetMapping("/companies")
	public CompanyResponseDto getCompnayInfo(@RequestHeader("Authorization") String token) {
		return companyService.getCompanyInfo(token);
	}

	@PatchMapping("/companies")
	public ResponseEntity<CustomResponseEntity> updateCompany(@Valid @RequestBody CompanyUpdateDto companyUpdateDto, @RequestHeader("Authorization") String token) {
		companyService.updateCompany(companyUpdateDto, token);
		return CustomResponseEntity.toResponseEntity(StatusEnum.SUCCESS_COMPANY_UPDATE);
	}

}
