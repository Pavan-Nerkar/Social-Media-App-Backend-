package com.nerkar.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nerkar.models.Chat;
import com.nerkar.models.User;
import com.nerkar.repository.ChatRepository;

@Service
public class ChatServiceImplementation implements ChatService {

	@Autowired
	private ChatRepository chatRepository;
	
	@Override
	public Chat createChat(User reqUser, User user2) {
		
		Chat isExist = chatRepository.findChatByUsersId(reqUser, user2);
		
		if(isExist != null) {
			return isExist;
		}
		
		Chat chat = new Chat();
		chat.getUsers().add(user2);
		chat.getUsers().add(reqUser);
		chat.setTimestamp(LocalDateTime.now());
		
		return chatRepository.save(chat);
	}

	@Override
	public Chat findChatById(int chatId) throws Exception {
		
		Optional<Chat> opt = chatRepository.findById(chatId);
		
		if(opt.isEmpty()) {
			throw new Exception("Chat not Found with id "+chatId);
		}
		
		return opt.get();
	}

	@Override
	public List<Chat> findUsersChat(int userId) {
		
		return chatRepository.findByUsersId(userId);
	}

}
