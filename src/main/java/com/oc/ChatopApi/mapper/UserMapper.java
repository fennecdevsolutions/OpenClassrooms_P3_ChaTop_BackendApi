package com.oc.chatopapi.mapper;

import org.mapstruct.Mapper;

import com.oc.chatopapi.dto.UserCurrentDto;
import com.oc.chatopapi.dto.UserRegisterDto;
import com.oc.chatopapi.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UserCurrentDto toCurrentUserDto(User user);
	
	// DTO to entity 
	User toEntity (UserRegisterDto registerDto);
	

}
