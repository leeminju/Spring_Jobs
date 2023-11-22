package com.example.spring_jobs.user.service;

import com.example.spring_jobs.auth.exception.CustomException;
import com.example.spring_jobs.auth.jwt.JwtUtil;
import com.example.spring_jobs.auth.security.UserDetailsServiceImpl;
import com.example.spring_jobs.common.StatusEnum;
import com.example.spring_jobs.user.UserRoleEnum;
import com.example.spring_jobs.user.dto.LoginRequestDto;
import com.example.spring_jobs.user.dto.UserSignupRequestDto;
import com.example.spring_jobs.user.repository.UserRepository;
import com.example.spring_jobs.user.entity.User;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;

    public void signup(UserSignupRequestDto userSignupRequestDto) {
        String loginId = userSignupRequestDto.getLoginId();
        String password = passwordEncoder.encode(userSignupRequestDto.getPassword());

        // 회원 중복 확인
        Optional<User> checkLoginId = userRepository.findByLoginId(loginId);
        if (checkLoginId.isPresent()) {
            throw new CustomException(StatusEnum.DUPLICATED_LOGIN_ID);
        }

        // email 중복확인
        String email = userSignupRequestDto.getEmail();
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new CustomException(StatusEnum.DUPLICATED_EMAIL);
        }

        // 사용자 등록
        User user = User.builder().loginId(loginId).password(password).email(email).phone(userSignupRequestDto.getPhone()).role(UserRoleEnum.USER).build();
        userRepository.save(user);
    }


    public String login(LoginRequestDto loginRequestDto) {

        String loginId = loginRequestDto.getLoginId();
        String password = loginRequestDto.getPassword();

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginId);
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new CustomException(StatusEnum.BadCredentialsException);
        }

        return jwtUtil.createToken(loginId, UserRoleEnum.valueOf(loginRequestDto.getRole()));
    }
}