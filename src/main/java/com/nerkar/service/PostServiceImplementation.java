package com.nerkar.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.nerkar.models.Post;
import com.nerkar.models.User;

import com.nerkar.repository.PostRepository;
import com.nerkar.repository.UserRepository;

@Service
public class PostServiceImplementation implements PostService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	UserService userService;

	@Override
	public Post createNewPost(Post post, int userId) throws Exception {
		
		User user = userService.findUserById(userId);
		
		Post newPost = new Post();
		newPost.setCaption(post.getCaption());
		newPost.setImage(post.getImage());
		newPost.setVideo(post.getVideo());
		newPost.setCreatedAt(LocalDateTime.now());
		newPost.setUser(user);
		
		return postRepository.save(newPost);
	}

	@Override
	public String deletePost(int postId, int userId) throws Exception {
		
		Post post = findPostById(postId);
		User user = userService.findUserById(userId);
		
		if(post.getUser().getId() != user.getId()) {
			throw new Exception("You can't delete another users post");
		}
		
		postRepository.delete(post);
		
		return "Post deleted successfully";
	}

	@Override
	public List<Post> findPostByUserId(int userId) {
		
		return postRepository.findPostByUserId(userId);
	}

	@Override
	public Post findPostById(int postId) throws Exception {
		Optional<Post> opt = postRepository.findById(postId);
		if(opt.isEmpty()) {
			throw new Exception("Post not Found With Id "+postId);
		}
		return opt.get();
	}

	@Override
	public List<Post> findAllPost() {
		
		return postRepository.findAll();
	}

	@Override
	public Post savedPost(int postId, int userId) throws Exception {
		
		Post post = findPostById(postId);
		User user = userService.findUserById(userId);
		
		if(user.getSavedPost().contains(post)) {
			user.getSavedPost().remove(post);
		}else {
			user.getSavedPost().add(post);
		}
		
		userRepository.save(user);
		
		return post;
	}

	@Override
	public Post likePost(int postId, int userId) throws Exception {
		
		Post post = findPostById(postId);
		User user = userService.findUserById(userId);
		
		if(post.getLiked().contains(post)) {
			post.getLiked().remove(user);
		}else {
			post.getLiked().add(user);
		}
		
		return postRepository.save(post);
	}
	
	

}
