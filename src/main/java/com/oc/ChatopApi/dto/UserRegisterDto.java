package com.oc.chatopapi.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRegisterDto {
	
	private String name;
	private String email;
	private String password;

}
