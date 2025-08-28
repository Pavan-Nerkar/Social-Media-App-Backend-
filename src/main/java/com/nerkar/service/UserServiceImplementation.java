package com.nerkar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nerkar.config.JwtProvider;
import com.nerkar.exceptions.UserException;
import com.nerkar.models.User;
import com.nerkar.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User registerUser(User user) {
		User newUser = new User();

		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setId(user.getId());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(user.getPassword());

		User saveUser = userRepository.save(newUser);

		return saveUser;
	}

	@Override
	public User findUserById(int userId) throws UserException {
		Optional<User> user = userRepository.findById(userId); // user mathe fact id gheli database mathun purn user nahi.

		if (user.isPresent()) {
			return user.get(); // user javal id hoti manun purn user .get() ne fatch kela.
		}

		throw new UserException("User is not Exist with userId " + userId);
	}

	@Override
	public User findUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}

	@Override
	public User followUser(int reqUserId, int userId2) throws UserException {
		
		User reqUser = findUserById(reqUserId);
		
		User user2 = findUserById(userId2);
		
		reqUser.getFollowings().add(user2.getId());
		user2.getFollowers().add(reqUser.getId());
		
		
		userRepository.save(reqUser);
		userRepository.save(user2);
		
		return reqUser;
	}

	@Override
	public User updateUser(User user, int userId) throws UserException {
		
		Optional<User> user1 = userRepository.findById(userId);

		if (user1.isEmpty()) {
			throw new UserException("User not Exist with id " + userId);
		}

		User oldUser = user1.get();

		if (user.getEmail() != null) {
			oldUser.setEmail(user.getEmail());
		}

		if (user.getFirstName() != null) {
			oldUser.setFirstName(user.getFirstName());
		}
		if (user.getLastName() != null) {
			oldUser.setLastName(user.getLastName());
		}
		
		if(user.getGender() !=null) {
			oldUser.setGender(user.getGender());
		}

		User updatedUser = userRepository.save(oldUser);

		return updatedUser;
	}

	@Override
	public List<User> searchUser(String query) {
		
		return userRepository.searchUser(query);
		
	}

	@Override
	public User findUserByJwt(String jwt) {
		
		String pureToken = jwt.substring(7); // ✅ Remove "Bearer " prefix

		String email = JwtProvider.getEmailFromJwtToken(pureToken); // ✅ Only token passed
		
		User user = userRepository.findByEmail(email);
		
		return user;
	}
	
	

}
