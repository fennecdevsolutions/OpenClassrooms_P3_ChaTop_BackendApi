package com.oc.ChatopApi.controller;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public Map<String, String> registerUser (@RequestBody User user) {
		// insert user into database
		userService.saveUser(user);
		// generate token
		String token = jwtService.generateToken(user.getEmail());
		// return token for the frontend in JSON format {"token": "token_value"}
		return Collections.singletonMap("token", token);
	}
	
	// Get connected/created user
	@GetMapping("/me")
	public User getCurrentUser (Principal principal){
		// returns the connected user using the Spring Security "principal"
		return userService.findUserByEmail(principal.getName());
	
	}

}
