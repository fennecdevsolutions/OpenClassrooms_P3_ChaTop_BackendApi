package com.oc.chatopapi.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.oc.chatopapi.dto.ApiErrorDto;
import com.oc.chatopapi.exception.InvalidCredentialsException;
import com.oc.chatopapi.exception.MessageSendingException;
import com.oc.chatopapi.exception.UserAlreadyExistsException;
import com.oc.chatopapi.exception.NotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ApiErrorDto> handleNotFound(NotFoundException ex, HttpServletRequest request) {
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
	
	@ExceptionHandler(MessageSendingException.class)
	public ResponseEntity<ApiErrorDto> handleMessageSending(MessageSendingException ex, HttpServletRequest request) {
		ApiErrorDto error = new ApiErrorDto(
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR.name(),
				ex.getMessage(),
				request.getRequestURI());
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(error);
		
	}
	

}
