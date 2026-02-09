package com.oc.chatopapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oc.chatopapi.dto.ApiErrorDto;
import com.oc.chatopapi.dto.MessageDto;
import com.oc.chatopapi.dto.SuccessMessageDto;
import com.oc.chatopapi.service.MessageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/api/messages")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	// Send message 
	@Operation (summary = "Send Message", description = "Send new message and return success message")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Message sent successfully",
	    			content = @Content(schema = @Schema(implementation = SuccessMessageDto.class))),
	    @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid or missing token",
	               	content = @Content(schema = @Schema(implementation = ApiErrorDto.class))),
	    @ApiResponse(responseCode = "500", description = "Internal Server Error - Unexpected server exception",
       	content = @Content(schema = @Schema(implementation = ApiErrorDto.class)))
		})
	@PostMapping
	public SuccessMessageDto sendMessage (@RequestBody MessageDto messageDto) {
		return messageService.sendMsg(messageDto);
	}

}
