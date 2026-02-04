package com.oc.ChatopApi.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.oc.ChatopApi.dto.ApiErrorDto;
import com.oc.ChatopApi.exception.InvalidCredentialsException;
import com.oc.ChatopApi.exception.UserAlreadyExistsException;
import com.oc.ChatopApi.exception.UserNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiErrorDto> handleUserNotFound(UserNotFoundException ex, HttpServletRequest request) {
		ApiErrorDto error = new ApiErrorDto(
				HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND.name(),
				ex.getMessage(),
				request.getRequestURI());
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(error);
				}
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<ApiErrorDto> handleUserAlreadyExists(UserAlreadyExistsException ex, HttpServletRequest request) {
		ApiErrorDto error = new ApiErrorDto(
				HttpStatus.CONFLICT.value(),
				HttpStatus.CONFLICT.name(),
				ex.getMessage(),
				request.getRequestURI());
		return ResponseEntity
				.status(HttpStatus.CONFLICT)
				.body(error);
		
	}
	
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ApiErrorDto> handleInvalidCredentials(InvalidCredentialsException ex, HttpServletRequest request) {
		ApiErrorDto error = new ApiErrorDto(
				HttpStatus.UNAUTHORIZED.value(),
				HttpStatus.UNAUTHORIZED.name(),
				ex.getMessage(),
				request.getRequestURI());
		return ResponseEntity
				.status(HttpStatus.UNAUTHORIZED)
				.body(error);
		
	}

}
