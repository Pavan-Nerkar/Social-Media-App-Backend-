package com.nerkar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.nerkar.models.Comment;
import com.nerkar.models.User;
import com.nerkar.service.CommentService;
import com.nerkar.service.UserService;

@RestController
public class CommentController {
	
	@Autowired
	private CommentService commetService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/comments/post/{postId}")
	public Comment createComment(@RequestBody Comment comment,
			@RequestHeader("Authorization") String jwt, @PathVariable("postId") int postId) throws Exception {
		
		User user = userService.findUserByJwt(jwt);
		
		Comment createdComment = commetService.createComments(comment, postId, user.getId());
		
		return createdComment;
	}
	
	
	@PutMapping("/api/comments/like/{commentId}")
	public Comment likeComment(@RequestBody Comment comment,
			@RequestHeader("Authorization") String jwt, @PathVariable int commentId) throws Exception {
		
		User user = userService.findUserByJwt(jwt);
		
		Comment likeComment = commetService.likeComment(commentId,user.getId());
		
		return likeComment;
	}

}
