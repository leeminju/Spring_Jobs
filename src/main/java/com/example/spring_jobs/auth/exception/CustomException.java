package com.example.spring_jobs.auth.exception;

import com.example.spring_jobs.common.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException{
	StatusEnum statusEnum;
}
