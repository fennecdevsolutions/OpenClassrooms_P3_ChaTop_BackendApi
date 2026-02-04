package com.oc.ChatopApi.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.oc.ChatopApi.dto.ApiError;
import com.oc.ChatopApi.exception.UserAlreadyExistsException;
import com.oc.ChatopApi.exception.UserNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiError> handleUserNotFound(UserNotFoundException ex, HttpServletRequest request) {
		ApiError error = new ApiError(
				HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND.name(),
				ex.getMessage(),
				request.getRequestURI());
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(error);
				}
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<ApiError> handleUserAlreadyExists(UserAlreadyExistsException ex, HttpServletRequest request) {
		ApiError error = new ApiError(
				HttpStatus.CONFLICT.value(),
				HttpStatus.CONFLICT.name(),
				ex.getMessage(),
				request.getRequestURI());
		return ResponseEntity
				.status(HttpStatus.CONFLICT)
				.body(error);
		
	}

}
