package com.nerkar.service;

import java.util.List;

import com.nerkar.models.Reels;
import com.nerkar.models.User;

public interface ReelsService {
	
	public Reels createReels(Reels reel, User user);
	
	public List<Reels> findAllReels();
	
	public List<Reels> findUserReels(int userId) throws Exception;
	
}
