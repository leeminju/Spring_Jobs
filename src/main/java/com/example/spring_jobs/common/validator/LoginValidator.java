package com.example.spring_jobs.common.validator;

import com.example.spring_jobs.common.annotation.LoginId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
public class LoginValidator implements ConstraintValidator<LoginId, String> {

    private static final int MIN_SIZE = 4;
    private static final int MAX_SIZE = 10;
    private static final String regexUsername = "^(?=.*\\d)[a-z0-9]{" + MIN_SIZE + "," + MAX_SIZE + "}$";

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        boolean isValidUsername = username.matches(regexUsername);
        if (!isValidUsername) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    MessageFormat.format("{0}자 이상의 {1}자 이하의 숫자, 영문자를 포함한 ID를 입력해주세요.",
                            MIN_SIZE,
                            MAX_SIZE)).addConstraintViolation();
        }
        return isValidUsername;
    }
}
