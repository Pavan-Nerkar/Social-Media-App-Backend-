package com.nerkar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.nerkar.models.Chat;
import com.nerkar.models.User;
import com.nerkar.request.CreateChatRequest;
import com.nerkar.service.ChatService;
import com.nerkar.service.UserService;

@RestController
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/api/chats")
	public Chat createChat(@RequestHeader("Authorization") String jwt , @RequestBody CreateChatRequest req) throws Exception {
		
		User reqUser = userService.findUserByJwt(jwt);
		User user2 = userService.findUserById(req.getUserId());
		
		Chat chat = chatService.createChat(reqUser, user2);
		
		return chat;
	}
	
	@GetMapping("/api/chats")
	public List<Chat> findUsersChat(@RequestHeader("Authorization") String jwt) {
		
		User reqUser = userService.findUserByJwt(jwt);
		
		List<Chat> Chats = chatService.findUsersChat(reqUser.getId());  //react page me Search kerne par reqUser ne kitane logose Chat ki hai uski list
		
		return Chats;
	}

}
