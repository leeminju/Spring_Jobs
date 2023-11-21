package com.example.spring_jobs.auth.exception;

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
		String name = exception.getBindingResult().getAllErrors().get(0).getClass().getSimpleName();
		return CustomResponseEntity.toResponseEntity(StatusEnum.valueOf(name));
	}
}
