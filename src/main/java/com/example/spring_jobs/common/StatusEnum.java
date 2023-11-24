package com.example.spring_jobs.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum StatusEnum {
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "POST_NOT_FOUND", "게시글이 존재하지 않습니다."),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "COMMENT_NOT_FOUND", "댓글이 존재하지 않습니다."),
    POST_NOT_MATCHED(HttpStatus.UNAUTHORIZED, "POST_NOT_MATCHED", "게시글을 작성한 사용자가 아닙니다."),
    SUCCESS_JOIN(HttpStatus.CREATED, "SUCCESS_JOIN", "회원가입에 성공하였습니다."),
    SUCCESS_LOGIN(HttpStatus.OK, "SUCCESS_LOGIN", "로그인에 성공하였습니다."),
    SUCCESS_USER_UPDATE(HttpStatus.OK, "SUCCESS_USER_UPDATE", "개인 회원 정보 수정에 성공하였습니다."),
    SUCCESS_COMPANY_UPDATE(HttpStatus.OK, "SUCCESS_COMPANY_UPDATE", "기업 회원 정보 수정에 성공하였습니다."),
    SUCCESS_CHANGE_PASSWORD(HttpStatus.OK, "SUCCESS_CHANGE_PASSWORD", "비밀번호가 변경되었습니다"),
    ROLE_NOT_COMPANY(HttpStatus.UNAUTHORIZED, "ROLE_NOT_COMPANY", "기업 회원이 아닙니다."),
    SUCCESS_ADD_POST(HttpStatus.OK, "SUCCESS_ADD_POST", "공고가 등록되었습니다."),
    SUCCESS_UPDATE_POST(HttpStatus.OK, "SUCCESS_UPDATE_POST", "공고가 수정되었습니다."),
    SUCCESS_DELETE_POST(HttpStatus.OK, "SUCCESS_DELETE_POST", "공고가 삭제되었습니다."),
    SUCCESS_COMMENT_CREATE(HttpStatus.OK, "SUCCESS_COMMENT_CREATE", "댓글이 등록되었습니다."),
    SUCCESS_COMMENT_UPDATE(HttpStatus.OK, "SUCCESS_COMMENT_UPDATE", "댓글이 수정되었습니다."),
    SUCCESS_COMMENT_DELETE(HttpStatus.OK, "SUCCESS_DELETE_POST", "댓글이 삭제되었습니다."),
    DUPLICATED_LOGIN_ID(HttpStatus.CONFLICT, "DUPLICATED_LOGIN_ID", "중복된 아이디가 존재합니다."),
    DUPLICATED_NICKNAME(HttpStatus.CONFLICT, "DUPLICATED_NICKNAME", "중복된 닉네임이 존재합니다."),
    DUPLICATED_PHONENUM(HttpStatus.CONFLICT, "DUPLICATED_PHONENUM", "중복된 전화번호이 존재합니다."),
    DUPLICATED_COMPANY_NAME(HttpStatus.CONFLICT, "DUPLICATED_COMPANY_NAME", "중복된 기업명이 존재합니다."),
    DUPLICATED_EMAIL(HttpStatus.CONFLICT, "DUPLICATED_EMAIL", "중복된 이메일이 존재합니다."),
    BadCredentialsException(HttpStatus.UNAUTHORIZED, "PASSWORD_NOT_MATCHED", "비밀번호가 일치하지 않습니다."),
    NotEqualsCheckPassWordException(HttpStatus.BAD_REQUEST, "PASSWORD_CHECK_FAIL", "새 비밀번호와 비밀번호 확인이 일치하지 않습니다."),
    EqualsCURRENTPassWordException(HttpStatus.BAD_REQUEST, "PASSWORD_EQUALS_CURRENT", "현재 비밀번호와 동일합니다."),
    UsernameNotFoundException(HttpStatus.BAD_REQUEST, "USER_NOT_FOUND", "등록된 아이디가 존재하지 않습니다."),
    TOKEN_NOT_VALID(HttpStatus.UNAUTHORIZED, "TOKEN_NOT_VALID", "토큰이 유효하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String description;
    private final String message;
}
