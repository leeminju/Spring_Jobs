package com.example.spring_jobs.common.exception;

import com.example.spring_jobs.common.CustomResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<CustomResponseEntity> handleCustomException(CustomException exception){
		return CustomResponseEntity.toResponseEntity(exception.getStatusEnum());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomResponseEntity> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		return CustomResponseEntity.toResponseEntity(exception);
	}
}
