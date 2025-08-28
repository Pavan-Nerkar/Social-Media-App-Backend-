package com.nerkar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.nerkar.models.Message;
import com.nerkar.models.User;
import com.nerkar.service.MessageService;
import com.nerkar.service.UserService;

@RestController
public class CreateMessage {
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/messages/chat/{chatId}")
	public Message createMessage(@RequestBody Message mess,@PathVariable int chatId ,@RequestHeader("Authorization") String jwt) throws Exception {
		
		User reqUser = userService.findUserByJwt(jwt);
		
		Message message = messageService.createMessage(reqUser, chatId, mess);
		
		return message;
	}
	
	@GetMapping("/api/messages/chat/{chatId}")
	public List<Message> findChatMessage(@PathVariable int chatId) throws Exception{
		
		List<Message> messages = messageService.findChatsMessages(chatId);
		
		return messages;
	}
	

}
