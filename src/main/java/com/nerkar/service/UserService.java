package com.nerkar.service;

import java.util.List;

import com.nerkar.exceptions.UserException;
import com.nerkar.models.User;

public interface UserService {
	
	public User registerUser(User user); //parameter mathala user frontend se ayega
	
	public User findUserById(int userId) throws UserException;
	
	public User findUserByEmail(String email);
	
	public User followUser(int userId1, int userId2) throws UserException;
	
	public User updateUser(User user, int userId) throws UserException;
	
	public List<User> searchUser(String query);
	
	public User findUserByJwt(String jwt);
	
	
	
	
}
