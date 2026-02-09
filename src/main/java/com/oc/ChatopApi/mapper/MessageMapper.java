package com.oc.chatopapi.mapper;

import org.mapstruct.Mapper;

import com.oc.chatopapi.dto.MessageDto;
import com.oc.chatopapi.model.Message;

@Mapper(componentModel = "spring")
public interface MessageMapper {
	Message toMessageEntity (MessageDto message);

}
