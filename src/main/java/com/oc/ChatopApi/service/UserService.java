package com.oc.chatopapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.oc.chatopapi.dto.TokenDto;
import com.oc.chatopapi.exception.InvalidCredentialsException;
import com.oc.chatopapi.exception.UserAlreadyExistsException;
import com.oc.chatopapi.exception.UserNotFoundException;
import com.oc.chatopapi.model.User;
import com.oc.chatopapi.repository.UserRepository;

import lombok.Data;

@Data
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder pwEncoder;
	
	@Autowired
	private JWTService jwtService;
	
	
	// Insert new user in database if email does not exist
	public TokenDto saveUser (User newUser) {
		// Check if user already exists
		if(userRepo.existsByEmail(newUser.getEmail())) {
			throw new UserAlreadyExistsException(
					"User with email " + newUser.getEmail() + " already exists!");		
		}
		//encode password before then store user in DB
		String encodedPw = pwEncoder.encode(newUser.getPassword()); 
		newUser.setPassword(encodedPw);
		userRepo.save(newUser);
		// generate token and return it
		String token = jwtService.generateToken(newUser.getEmail());
		return new TokenDto (token);	
	}
	
	// fetch user by email 
	public User findUserByEmail(String email) {
		
		return userRepo.findByEmail(email)
				.orElseThrow(()-> new UserNotFoundException("User with email " + email + " not found"));
	}
	
	
	// Login user and return JWT token if password match
	public TokenDto loginUser (User user) {
		User registeredUser = userRepo.findByEmail(user.getEmail())
				.orElseThrow(()-> new InvalidCredentialsException("Invalid email or password"));
	// verify password
		if(!pwEncoder.matches(user.getPassword(),registeredUser.getPassword())) {
			throw new InvalidCredentialsException("Invalid email or password");
		}
		
		String token = jwtService.generateToken(registeredUser.getEmail());
		return new TokenDto (token);
		
	}
	

}
