package com.nerkar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nerkar.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByEmail(String email);
	
	@Query("SELECT u FROM User u WHERE u.firstName LIKE CONCAT('%', :query, '%') OR u.lastName LIKE CONCAT('%', :query, '%') OR u.email LIKE CONCAT('%', :query, '%')")
	public List<User> searchUser(@Param("query") String query);

	
}
 