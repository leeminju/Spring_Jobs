package com.example.spring_jobs.company.service;

import com.example.spring_jobs.auth.exception.CustomException;
import com.example.spring_jobs.common.StatusEnum;
import com.example.spring_jobs.company.dto.CompanySignupRequestDto;
import com.example.spring_jobs.company.entity.Company;
import com.example.spring_jobs.company.repository.CompanyRepository;
import com.example.spring_jobs.user.UserRoleEnum;
import com.example.spring_jobs.user.entity.User;
import com.example.spring_jobs.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CompanyRepository companyRepository;

    public void signup(CompanySignupRequestDto companySignupRequestDto) {
        String loginId = companySignupRequestDto.getLoginId();
        String password = passwordEncoder.encode(companySignupRequestDto.getPassword());

        // 회원 중복 확인
        Optional<User> checkLoginId = userRepository.findByLoginId(loginId);
        if (checkLoginId.isPresent()) {
            throw new CustomException(StatusEnum.DUPLICATED_LOGIN_ID);
        }

        // email 중복확인
        String email = companySignupRequestDto.getEmail();
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new CustomException(StatusEnum.DUPLICATED_EMAIL);
        }

        //기업명 중복 확인
        String companyName = companySignupRequestDto.getCompanyName();
        Optional<Company> checkCompanyName = companyRepository.findByCompanyName(companyName);
        if (checkCompanyName.isPresent()) {
            throw new CustomException(StatusEnum.DUPLICATED_COMPANY_NAME);
        }
        User user = User.builder()
                .loginId(loginId)
                .password(password)
                .email(email)
                .phone(companySignupRequestDto.getPhone())
                .role(UserRoleEnum.COMPANY).build();
        Company company = Company.builder()
                        .companyName(companyName)
                        .industry(companySignupRequestDto.getIndustry())
                        .location(companySignupRequestDto.getLocation())
                        .user(user).build();

        companyRepository.save(company);
    }
}
