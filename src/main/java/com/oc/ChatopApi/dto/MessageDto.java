package com.oc.chatopapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageDto {

	private Integer rental_id;
	private Integer user_id;
	private String message;

}
