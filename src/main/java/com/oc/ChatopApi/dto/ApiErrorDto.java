package com.oc.chatopapi.dto;

import java.time.Instant;

import lombok.Getter;

@Getter
public class ApiErrorDto {
	private final Instant timestamps = Instant.now();
	private final int status;
	private final String error;
	private final String message;
	private final String path;
	
	public ApiErrorDto (int status, String error, String message, String path) {
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

}
