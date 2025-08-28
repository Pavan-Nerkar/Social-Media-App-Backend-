package com.nerkar.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nerkar.models.Chat;
import com.nerkar.models.Message;
import com.nerkar.models.User;
import com.nerkar.repository.ChatRepository;
import com.nerkar.repository.MessageRepository;

@Service
public class MessageServiceImplementation implements MessageService {

	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private ChatRepository chatRepository;
	
	@Override
	public Message createMessage(User user, int chatId, Message mess) throws Exception {
		
		Chat chat = chatService.findChatById(chatId);
		Message message = new Message();
		
		message.setChat(chat);
		message.setContent(mess.getContent());
		message.setImage(mess.getImage());
		message.setUser(user);
		message.setTimestamp(LocalDateTime.now());
		
		Message saveMessage = messageRepository.save(message);
		
		chat.getMessages().add(saveMessage);
		chatRepository.save(chat); 
		
		return saveMessage;
	}

	@Override
	public List<Message> findChatsMessages(int chatId) throws Exception {
		
		Chat chat =chatService.findChatById(chatId);
		
		return messageRepository.findByChatId(chatId);
	}

}
