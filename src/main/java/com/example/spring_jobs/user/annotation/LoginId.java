package com.example.spring_jobs.user.annotation;

import com.example.spring_jobs.user.validator.LoginValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LoginValidator.class)
@Documented
public @interface LoginId {

	String message() default "ID는 최소 4자 이상, 10자 이하이며 알파벳 소문자, 숫자로 구성되어야 합니다.";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
