package com.oc.ChatopApi.controller;

import java.security.Principal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oc.ChatopApi.dto.RegisterDto;
import com.oc.ChatopApi.dto.UserDto;
import com.oc.ChatopApi.model.User;
import com.oc.ChatopApi.service.JWTService;
import com.oc.ChatopApi.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private JWTService jwtService;
	
	// Register New User
	
	@PostMapping ("/register")
	public RegisterDto registerUser (@RequestBody User user) {
		// insert user into database
		userService.saveUser(user);
		// generate token
		String token = jwtService.generateToken(user.getEmail());
		// return token for the front end in JSON format {"token": "token_value"}
		return new RegisterDto (token);
	}
	
	// Get connected/created user
	@GetMapping("/me")
	public UserDto getCurrentUser (Principal principal){
		User fetchedUser = userService.findUserByEmail(principal.getName());
		return new UserDto (
				fetchedUser.getId(),
				fetchedUser.getName(),
				fetchedUser.getEmail(),
				fetchedUser.getCreated_at(),
				fetchedUser.getUpdated_at()) ;
	
	}

}
