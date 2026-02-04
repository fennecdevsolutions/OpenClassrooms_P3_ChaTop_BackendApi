package com.oc.ChatopApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
	
	public User saveUser (User newUser) {
		
		//encode password before storage in DB
		String encodedPw = pwEncoder.encode(newUser.getPassword()); 
		newUser.setPassword(encodedPw);
		User savedUser = userRepo.save(newUser);
		return savedUser;
	}
	
	public User findUserByEmail(String email) {
		
		return userRepo.findByEmail(email)
				.orElseThrow(()-> new RuntimeException("User not found"));
	}

}
