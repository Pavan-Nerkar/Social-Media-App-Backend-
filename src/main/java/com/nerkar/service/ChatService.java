package com.nerkar.service;

import java.util.List;

import com.nerkar.models.Chat;
import com.nerkar.models.User;

public interface ChatService {
	
	public Chat createChat(User reqUser, User user2);
	
	public Chat findChatById(int chatId) throws Exception;
	
	public List<Chat> findUsersChat(int userId);

}
