package com.example.spring_jobs.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum StatusEnum {
    BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "BOARD_NOT_FOUND","게시글이 존재하지 않습니다."),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "COMMENT_NOT_FOUND","댓글이 존재하지 않습니다."),
    BOARD_NOT_MATCHED(HttpStatus.UNAUTHORIZED, "BOARD_NOT_MATCHED", "게시글을 작성한 사용자가 아닙니다."),
    SUCCESS_JOIN(HttpStatus.CREATED,"SUCCESS_JOIN", "회원가입에 성공하였습니다."),
    SUCCESS_LOGIN(HttpStatus.OK,"SUCCESS_LOGIN", "로그인에 성공하였습니다."),
    DUPLICATED_LOGIN_ID(HttpStatus.CONFLICT, "DUPLICATED_LOGIN_ID", "중복된 아이디가 존재합니다."),
    DUPLICATED_COMPANY_NAME(HttpStatus.CONFLICT, "DUPLICATED_COMPANY_NAME", "중복된 기업명이 존재합니다."),
    DUPLICATED_EMAIL(HttpStatus.CONFLICT, "DUPLICATED_EMAIL", "중복된 이메일이 존재합니다."),
    BadCredentialsException(HttpStatus.UNAUTHORIZED, "PASSWORD_NOT_MATCHED", "비밀번호가 일치하지 않습니다."),
    UsernameNotFoundException(HttpStatus.BAD_REQUEST, "USER_NOT_FOUND","등록된 아이디가 존재하지 않습니다."),
    TOKEN_NOT_VALID(HttpStatus.UNAUTHORIZED, "TOKEN_NOT_VALID","토큰이 유효하지 않습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String description;
    private final String message;
}
