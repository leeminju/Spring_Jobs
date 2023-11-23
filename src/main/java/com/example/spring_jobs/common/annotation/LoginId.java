package com.example.spring_jobs.common.annotation;

import com.example.spring_jobs.common.validator.LoginValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LoginValidator.class)
@Documented
public @interface LoginId {

	String message() default "ID는 최소 4자 이상, 10자 이하이며 알파벳 소문자, 숫자로 구성되어야 합니다.";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
