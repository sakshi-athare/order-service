package com.demo.exception;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.demo.dto.exception.ErrorResponseDto;


@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ProductNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponseDto handleProductNotFound(ProductNotFoundException ex, HttpServletRequest request) {

		return buildResponse(ex, request, 404);
	}

	private ErrorResponseDto buildResponse(Exception ex, HttpServletRequest request, int statusCode) {

		ErrorResponseDto response = new ErrorResponseDto();
		response.setMessage(ex.getMessage());
		response.setStatusCode(statusCode);
		response.setTimeStamp(LocalDateTime.now());
		response.setPath(request.getRequestURI());

		return response;
	}

	@ExceptionHandler(UserServiceDownException.class)
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	public ErrorResponseDto handleUserServiceDown(UserServiceDownException ex, HttpServletRequest request) {

		return buildResponse(ex, request, 503);
	}

	@ExceptionHandler(ProductServiceDownException.class)
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	public ErrorResponseDto handleProductServiceDown(ProductServiceDownException ex, HttpServletRequest request) {

		return buildResponse(ex, request, 503);
	}

	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponseDto handleUserNotFound(UserNotFoundException ex, HttpServletRequest request) {

		return buildResponse(ex, request, 404);
	}

}
