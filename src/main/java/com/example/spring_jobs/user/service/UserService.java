package com.example.spring_jobs.user.service;

import com.example.spring_jobs.auth.jwt.JwtUtil;
import com.example.spring_jobs.auth.security.UserDetailsImpl;
import com.example.spring_jobs.auth.security.UserDetailsServiceImpl;
import com.example.spring_jobs.common.StatusEnum;
import com.example.spring_jobs.common.exception.CustomException;
import com.example.spring_jobs.user.UserRoleEnum;
import com.example.spring_jobs.user.dto.*;
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
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;


    public void signup(UserSignupRequestDto userSignupRequestDto) {
        String loginId = userSignupRequestDto.getLoginId();
        String password = passwordEncoder.encode(userSignupRequestDto.getPassword());

        // 회원 중복 확인
        checkLoginId(loginId);
        // 닉네임 중복 확인
        String nickname = userSignupRequestDto.getNickname();
        checkNickname(nickname);
        // email 중복확인
        String email = userSignupRequestDto.getEmail();
        checkEmail(email);
        String phone = userSignupRequestDto.getPhone();
        checkPhone(phone);

        // 사용자 등록
        User user = User.builder()
                .loginId(loginId)
                .password(password)
                .email(email)
                .nickname(nickname)
                .phone(userSignupRequestDto.getPhone())
                .role(UserRoleEnum.USER).build();
        userRepository.save(user);
    }

    public String login(LoginRequestDto loginRequestDto) {

        String loginId = loginRequestDto.getLoginId();

        Optional<User> checkID = userRepository.findByLoginId(loginId);
        if (checkID.isEmpty()) {
            throw new CustomException(StatusEnum.UsernameNotFoundException);
        }

        String password = loginRequestDto.getPassword();

        UserDetailsImpl userDetails = userDetailsService.loadUserByUsername(loginId);
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new CustomException(StatusEnum.BadCredentialsException);
        }

        return jwtUtil.createToken(loginId, userDetails.getUser().getRole());
    }

    public UserResponseDto getUserInfo(String loginId) {
        User user = findUser(loginId);
        return new UserResponseDto(user);
    }

    @Transactional
    public void updateUser(UserUpdateDto userUpdateDto, String loginId) {
        User user = findUser(loginId);

         // 같은 값으로 업데이트 가능하지만 다른 값으로 업데이트 시 중복체크 해줘야함
        if(!user.getNickname().equals(userUpdateDto.getNickname())) {
            checkNickname(userUpdateDto.getNickname());
        }
        if(!user.getEmail().equals(userUpdateDto.getEmail())) {
            checkEmail(userUpdateDto.getEmail());
        }
        if(!user.getPhone().equals(userUpdateDto.getPhone())) {
            checkPhone(userUpdateDto.getPhone());
        }

        user.updateInfo(userUpdateDto);
    }

    private User findUser(String loginId) {
        User findUser = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new CustomException(StatusEnum.UsernameNotFoundException));
        return findUser;
    }

    @Transactional
    public void updatePassword(PasswordRequestDto passwordRequestDto, User user) {
        String currentPassword = passwordRequestDto.getCurrentPassword();

        User dbUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new CustomException(StatusEnum.UsernameNotFoundException)
        );
        //현재 비밀번호 일치 여부 확인
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new CustomException(StatusEnum.BadCredentialsException);
        }
        //새 비밀번호와 확인 비밀번호가 일치 여부 확인
        if (!passwordRequestDto.getNewPassword().equals(passwordRequestDto.getCheckPassword())) {
            throw new CustomException(StatusEnum.NotEqualsCheckPassWordException);
        }

        //현재 비밀번호와 새 비밀번호가 일치 여부 확인
        if (passwordRequestDto.getNewPassword().equals(passwordRequestDto.getCurrentPassword())) {
            throw new CustomException(StatusEnum.EqualsCURRENTPassWordException);
        }

        String newPassword = passwordEncoder.encode(passwordRequestDto.getNewPassword());
        dbUser.changePassword(newPassword);
    }

    private void checkNickname(String nickname) {
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