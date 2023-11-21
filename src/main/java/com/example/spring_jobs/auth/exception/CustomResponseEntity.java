package com.example.spring_jobs.auth.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
@Builder
public class CustomResponseEntity {
	private int status;
	private String description;
	private String message;

	public static ResponseEntity<CustomResponseEntity> toResponseEntity(StatusEnum e){
		return ResponseEntity
			.status(e.getHttpStatus())
			.body(CustomResponseEntity.builder()
				.status(e.getHttpStatus().value())
				.description(e.name())
				.message(e.getMessage())
				.build());
	}
}
