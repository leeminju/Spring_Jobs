package com.example.spring_jobs.company.controller;

import com.example.spring_jobs.auth.security.UserDetailsImpl;
import com.example.spring_jobs.common.CustomResponseEntity;
import com.example.spring_jobs.common.StatusEnum;
import com.example.spring_jobs.company.dto.CompanyResponseDto;
import com.example.spring_jobs.company.dto.CompanySignupRequestDto;
import com.example.spring_jobs.company.dto.CompanyUpdateDto;
import com.example.spring_jobs.company.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
	public CompanyResponseDto getCompanyInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
		Long companyId = userDetails.getUser().getCompany().getId();
		return companyService.getCompanyInfo(companyId);
	}

	@PatchMapping("/companies")
	public ResponseEntity<CustomResponseEntity> updateCompany(@Valid @RequestBody CompanyUpdateDto companyUpdateDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		Long companyId = userDetails.getUser().getCompany().getId();
		companyService.updateCompany(companyUpdateDto, companyId);
		return CustomResponseEntity.toResponseEntity(StatusEnum.SUCCESS_COMPANY_UPDATE);
	}

}
