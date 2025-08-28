package com.nerkar.service;

import java.util.List;

import com.nerkar.models.Chat;
import com.nerkar.models.Message;
import com.nerkar.models.User;

public interface MessageService {
	
	public Message createMessage(User user, int chatId, Message mess) throws Exception;
	
	public List<Message> findChatsMessages(int chatId) throws Exception;	
}
