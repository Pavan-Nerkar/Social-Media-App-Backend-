package com.nerkar.service;



import com.nerkar.models.Comment;

public interface CommentService {
	
	public Comment createComments(Comment comment, int postId, int UserId) throws Exception;
		
	public Comment findCommentById(int commentId) throws Exception;
	
	public Comment likeComment(int CommentId, int userId) throws Exception;
	
}
