package com.example.spring_jobs.company.service;

import com.example.spring_jobs.auth.jwt.JwtUtil;
import com.example.spring_jobs.common.StatusEnum;
import com.example.spring_jobs.common.exception.CustomException;
import com.example.spring_jobs.company.dto.CompanyResponseDto;
import com.example.spring_jobs.company.dto.CompanySignupRequestDto;
import com.example.spring_jobs.company.dto.CompanyUpdateDto;
import com.example.spring_jobs.company.entity.Company;
import com.example.spring_jobs.company.repository.CompanyRepository;
import com.example.spring_jobs.user.UserRoleEnum;
import com.example.spring_jobs.user.entity.User;
import com.example.spring_jobs.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CompanyRepository companyRepository;
    private final JwtUtil jwtUtil;

    public void signup(CompanySignupRequestDto companySignupRequestDto) {
        String loginId = companySignupRequestDto.getLoginId();
        String password = passwordEncoder.encode(companySignupRequestDto.getPassword());

        // 회원 중복 확인
        checkLoginId(loginId);
        //기업명 중복 확인
        String companyName = companySignupRequestDto.getCompanyName();
        checkCompanyName(companyName);
        String nickname = companySignupRequestDto.getNickname();
        checkNicname(nickname);
        // email 중복확인
        String email = companySignupRequestDto.getEmail();
        checkEmail(email);

        String phone = companySignupRequestDto.getPhone();
        checkPhone(phone);

        User user = User.builder()
                .loginId(loginId)
                .nickname(nickname)
                .password(password)
                .email(email)
                .phone(phone)
                .role(UserRoleEnum.COMPANY).build();
        Company company = Company.builder()
                .companyName(companyName)
                .industry(companySignupRequestDto.getIndustry())
                .location(companySignupRequestDto.getLocation())
                .user(user).build();

        companyRepository.save(company);
    }

    public CompanyResponseDto getCompanyInfo(String loginId) {
        Company company = findCompany(loginId);
        return new CompanyResponseDto(company);
    }

    @Transactional
    public void updateCompany(CompanyUpdateDto companyUpdateDto, String loginId) {
        Company company = findCompany(loginId);

        // 같은 값으로 업데이트 가능하지만 다른 값으로 업데이트 시 중복체크 해줘야함
        if (!company.getCompanyName().equals(companyUpdateDto.getCompanyName())) {
            checkCompanyName(companyUpdateDto.getCompanyName());
        }
        if (!company.getUser().getNickname().equals(companyUpdateDto.getNickname())) {
            checkNicname(companyUpdateDto.getNickname());
        }
        if (!company.getUser().getEmail().equals(companyUpdateDto.getEmail())) {
            checkEmail(companyUpdateDto.getEmail());
        }
        if (!company.getUser().getPhone().equals(companyUpdateDto.getPhone())) {
            checkPhone(companyUpdateDto.getPhone());
        }

        company.updateInfo(companyUpdateDto);
    }

    private Company findCompany(String loginId) {
        Company findCompany = companyRepository.findByUserLoginId(loginId)
                .orElseThrow(() -> new CustomException(StatusEnum.UsernameNotFoundException));
        return findCompany;
    }

    private void checkNicname(String nickname) {
        Optional<User> checkNickname = userRepository.findByNickname(nickname);
        if (checkNickname.isPresent()) {
            throw new CustomException(StatusEnum.DUPLICATED_NICKNAME);
        }
    }

    private void checkEmail(String email) {
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new CustomException(StatusEnum.DUPLICATED_EMAIL);
        }
    }

    private void checkCompanyName(String companyName) {
        Optional<Company> checkCompanyName = companyRepository.findByCompanyName(companyName);
        if (checkCompanyName.isPresent()) {
            throw new CustomException(StatusEnum.DUPLICATED_COMPANY_NAME);
        }
    }

    private void checkLoginId(String loginId) {
        Optional<User> checkLoginId = userRepository.findByLoginId(loginId);
        if (checkLoginId.isPresent()) {
            throw new CustomException(StatusEnum.DUPLICATED_LOGIN_ID);
        }
    }

    private void checkPhone(String phone) {
        Optional<User> checkPhone = userRepository.findByPhone(phone);
        if (checkPhone.isPresent()) {
            throw new CustomException(StatusEnum.DUPLICATED_PHONENUM);
        }
    }
}
