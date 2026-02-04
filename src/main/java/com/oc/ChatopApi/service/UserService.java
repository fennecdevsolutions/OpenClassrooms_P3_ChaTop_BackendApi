package com.oc.ChatopApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.oc.ChatopApi.exception.UserAlreadyExistsException;
import com.oc.ChatopApi.exception.UserNotFoundException;
import com.oc.ChatopApi.model.User;
import com.oc.ChatopApi.repository.UserRepository;

import lombok.Data;

@Data
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder pwEncoder;
	
	
	// Insert new user in database if email does not exist
	public User saveUser (User newUser) {
		// Check if user already exists
		if(userRepo.existsByEmail(newUser.getEmail())) {
			throw new UserAlreadyExistsException(
					"User with email " + newUser.getEmail() + " already exists!");		
		}
		
		//encode password before storage in DB
		String encodedPw = pwEncoder.encode(newUser.getPassword()); 
		newUser.setPassword(encodedPw);
		User savedUser = userRepo.save(newUser);
		return savedUser;
	}
	
	// fetch user by email 
	public User findUserByEmail(String email) {
		
		return userRepo.findByEmail(email)
				.orElseThrow(()-> new UserNotFoundException("User with email " + email + " not found"));
	}

}
