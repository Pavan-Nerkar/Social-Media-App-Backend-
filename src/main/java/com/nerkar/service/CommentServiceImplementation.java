package com.nerkar.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.hibernate.annotations.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.nerkar.models.Comment;
import com.nerkar.models.Post;
import com.nerkar.repository.CommentRepository;
import com.nerkar.repository.PostRepository;

@Service
public class CommentServiceImplementation implements CommentService {

	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	
	@Override
	public Comment createComments(Comment comment, int postId, int UserId) throws Exception {
		
		com.nerkar.models.User user = userService.findUserById(UserId);
		
		Post post = postService.findPostById(postId);
		
		comment.setUser(user);
		comment.setContent(comment.getContent());
		comment.setCreatedAt(LocalDateTime.now());
		
		Comment savedComment = commentRepository.save(comment);
		
		post.getComments().add(savedComment);
		
		postRepository.save(post);

		return savedComment;
	}

	@Override
	public Comment findCommentById(int commentId) throws Exception {
		
		Optional<Comment> opt = commentRepository.findById(commentId);
		
		if(opt.isEmpty()) {
			throw new Exception("Comment not Exist");
		}
		 
		return opt.get();
	}

	@Override
	public Comment likeComment(int CommentId, int userId) throws Exception {
		
		Comment comment=findCommentById(CommentId);
		
		com.nerkar.models.User user = userService.findUserById(userId);
		
		if(!comment.getLiked().contains(user)) {
			comment.getLiked().add(user);
		}else {
			comment.getLiked().remove(user);
		}
		
		return commentRepository.save(comment);
	}

}
