package com.oc.ChatopApi.exception;

public class UserAlreadyExistsException extends RuntimeException{
	public UserAlreadyExistsException (String message) {
		super(message);
	}

}
