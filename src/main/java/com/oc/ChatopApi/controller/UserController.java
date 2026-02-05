package com.oc.chatopapi.controller;

import java.security.Principal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oc.chatopapi.dto.TokenDto;
import com.oc.chatopapi.dto.UserDto;
import com.oc.chatopapi.mapper.UserMapper;
import com.oc.chatopapi.model.User;
import com.oc.chatopapi.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private UserMapper userMapper;
	
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
		return userMapper.toDto(fetchedUser);
	
	}
	
	@PostMapping ("/login")
	public TokenDto loginUser (@RequestBody User user) {
		// Login user and get token
		return userService.loginUser(user);
	
	}

}
