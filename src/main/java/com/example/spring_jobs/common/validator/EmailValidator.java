package com.example.spring_jobs.common.validator;

import com.example.spring_jobs.common.annotation.Email;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class EmailValidator implements ConstraintValidator<Email, String> {
    private static final String regexEmail = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    // RFC 822를 업데이트한 RFC 5322의 정규식
    // 파이프 문자(|)와 작은따옴표(')는 허용되지 않습니다.
    // 클라이언트 사이트에서 서버로 전달될 때 잠재적인 SQL 주입 위험이 있기 때문입니다.

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        boolean isValidUsername = username.matches(regexEmail);
        if (!isValidUsername) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "올바른 이메일 형식을 입력해주세요.").addConstraintViolation();
        }
        return isValidUsername;
    }
}
