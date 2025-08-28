package com.nerkar.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nerkar.exceptions.UserException;
import com.nerkar.models.User;
import com.nerkar.repository.UserRepository;
import com.nerkar.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	
	
	
	@GetMapping("/api/users")
	public List<User> getUsers() {
		
		List<User> users = userRepository.findAll();
		
		return users;
	}
	
	@GetMapping("/api/users/{userId}")
	public User getUserById(@PathVariable("userId") int userId) throws UserException {
		
		User user = userService.findUserById(userId);
		
		return user;
		
	}
	
	
	
	@PutMapping("/api/users")
	public User updateUser(@RequestHeader("Authorization") String jwt ,@RequestBody User user) throws UserException {
		
		User reqUser = userService.findUserByJwt(jwt);
		
		User updateUser = userService.updateUser(user, reqUser.getId());
		
		return updateUser;
	}
	
	@PutMapping("/api/users/follow/{userId2}")
	public User followUserHandler(@RequestHeader("Authorization") String jwt, @PathVariable int userId2) throws UserException {
		
		User reqUser = userService.findUserByJwt(jwt);
		
		User user = userService.followUser(reqUser.getId(), userId2);
		
		return user;
	}
	
	@GetMapping("/api/users/search")
	public List<User> searchUsers(@RequestParam("query") String query){
			
		List<User> users = userService.searchUser(query);
		
		return users;
	}
	
	@GetMapping("/api/users/profile")
	public User getUserFromTokenUser(@RequestHeader("Authorization") String jwt) {
		
//		System.out.println("Jwt ----"+jwt);
		
		User user = userService.findUserByJwt(jwt);
		
		user.setPassword(null);
	
		return user;
	}
	
	

}
