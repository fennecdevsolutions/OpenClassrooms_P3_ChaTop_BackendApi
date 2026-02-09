package com.oc.chatopapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oc.chatopapi.dto.MessageDto;
import com.oc.chatopapi.dto.SuccessMessageDto;
import com.oc.chatopapi.exception.MessageSendingException;
import com.oc.chatopapi.mapper.MessageMapper;
import com.oc.chatopapi.model.Message;
import com.oc.chatopapi.repository.MessageRepository;

@Service
public class MessageService {
	@Autowired
	private MessageMapper messageMapper;
	@Autowired
	private MessageRepository messageRepo;
	
	// save message in database
	public SuccessMessageDto sendMsg (MessageDto message) {
		Message savedMessage= messageRepo.save(messageMapper.toMessageEntity(message));
		if (savedMessage.getId()==null) {
			throw new MessageSendingException("Error during message sending!");
			}
		return new SuccessMessageDto ("Message sent with success");
		}
}
