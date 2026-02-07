package com.oc.chatopapi.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserCurrentDto {
	private String email;
	private String name;
	private Timestamp created_at;
	private Timestamp updated_at;
}


