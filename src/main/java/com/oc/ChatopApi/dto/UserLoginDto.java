package com.oc.chatopapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLoginDto {
	private String email;
	private String password;
}
