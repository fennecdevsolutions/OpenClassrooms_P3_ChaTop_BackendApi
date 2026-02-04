package com.oc.ChatopApi.controller;

import java.security.Principal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oc.ChatopApi.dto.TokenDto;
import com.oc.ChatopApi.dto.UserDto;
import com.oc.ChatopApi.model.User;
import com.oc.ChatopApi.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// Register New User
	
	@PostMapping ("/register")
	public TokenDto registerUser (@RequestBody User user) {
		// insert user into database
		TokenDto token = userService.saveUser(user);
		return token;
	}
	
	// Get connected/created user
	@GetMapping("/me")
	public UserDto getCurrentUser (Principal principal){
		User fetchedUser = userService.findUserByEmail(principal.getName());
		return new UserDto (
				fetchedUser.getId(),
				fetchedUser.getEmail(),
				fetchedUser.getName(),
				fetchedUser.getCreated_at(),
				fetchedUser.getUpdated_at()) ;
	
	}
	
	@PostMapping ("/login")
	public TokenDto loginUser (@RequestBody User user) {
		// Login user and get token
		return userService.loginUser(user);
	
	}

}
