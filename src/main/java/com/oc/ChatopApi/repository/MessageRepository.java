package com.oc.chatopapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.oc.chatopapi.model.Message;

public interface MessageRepository extends CrudRepository <Message, Integer>{
	


}
