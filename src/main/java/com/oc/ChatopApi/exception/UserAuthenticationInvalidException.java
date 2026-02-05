package com.oc.chatopapi.exception;

public class UserAuthenticationInvalidException extends RuntimeException {
	public UserAuthenticationInvalidException (String message) {
		super(message);
	}

}
