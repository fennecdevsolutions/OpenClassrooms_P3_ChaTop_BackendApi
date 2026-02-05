package com.oc.chatopapi.mapper;

import org.mapstruct.Mapper;

import com.oc.chatopapi.dto.UserDto;
import com.oc.chatopapi.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UserDto toDto(User user);

}
