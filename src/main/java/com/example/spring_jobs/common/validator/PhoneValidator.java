package com.example.spring_jobs.common.validator;

import com.example.spring_jobs.common.annotation.Phone;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class PhoneValidator implements ConstraintValidator<Phone, String> {
    private static final String regexPhone = "^\\d{2,3}-\\d{3,4}-\\d{4}$";

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        boolean isValidUsername = username.matches(regexPhone);
        if (!isValidUsername) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "올바른 전화번호 형식을 입력해주세요.").addConstraintViolation();
        }
        return isValidUsername;
    }
}
