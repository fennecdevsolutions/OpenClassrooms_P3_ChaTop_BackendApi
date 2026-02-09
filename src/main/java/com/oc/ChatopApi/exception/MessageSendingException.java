package com.oc.chatopapi.exception;

public class MessageSendingException extends RuntimeException {
	public MessageSendingException (String message) {
		super(message);
	}

}
