package com.example.spring_jobs.user.annotation;

import com.example.spring_jobs.user.validator.EmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface Email {

    String message() default "이메일 형식에 맞지 않습니다.";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
