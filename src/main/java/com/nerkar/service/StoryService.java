package com.nerkar.service;

import java.util.List;

import com.nerkar.models.Story;
import com.nerkar.models.User;

public interface StoryService {
	
	public Story createStory(Story story, User user);
	
	public List<Story> findStoryByUserId(int userId) throws Exception;

}
