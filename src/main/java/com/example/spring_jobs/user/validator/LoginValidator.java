package com.example.spring_jobs.user.validator;

import com.example.spring_jobs.user.annotation.LoginId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.text.MessageFormat;

import org.springframework.stereotype.Component;

@Component
public class LoginValidator implements ConstraintValidator<LoginId, String> {

    private static final int MIN_SIZE = 4;
    private static final int MAX_SIZE = 10;
    private static final String regexUsername = "^[a-z0-9]+[a-z0-9]{" + MIN_SIZE + "," + MAX_SIZE + "}$";

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        boolean isValidUsername = username.matches(regexUsername);
        if (!isValidUsername) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    MessageFormat.format("{0}자 이상의 {1}자 이하의 숫자, 영문자를 포함한 이름을 입력해주세요.",
                            MIN_SIZE,
                            MAX_SIZE)).addConstraintViolation();
        }
        return isValidUsername;
    }
}
