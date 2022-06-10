package com.library.management.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Builder
@Getter
@Setter
public class ApiResponseDto {

	private int statusCode;
	private HttpStatus status;
	private String message;
	private Object data;

}
