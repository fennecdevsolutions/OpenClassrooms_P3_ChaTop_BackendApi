package com.oc.chatopapi.controller;

import java.security.Principal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oc.chatopapi.dto.ApiErrorDto;
import com.oc.chatopapi.dto.TokenDto;
import com.oc.chatopapi.dto.UserCurrentDto;
import com.oc.chatopapi.dto.UserLoginDto;
import com.oc.chatopapi.dto.UserRegisterDto;
import com.oc.chatopapi.mapper.UserMapper;
import com.oc.chatopapi.model.User;
import com.oc.chatopapi.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Endpoints for Users and Tokens management")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private UserMapper userMapper;
	
	// Register New User
	@Operation (summary = "Register User", description = "Create new user and return Token")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "201", description = "User registered successfully",
		                content = @Content(schema = @Schema(implementation = TokenDto.class))),
		    @ApiResponse(responseCode = "409", description = "User already exists",
		                content = @Content(schema = @Schema(implementation = ApiErrorDto.class)))
		})
	@PostMapping ("/register")
	public TokenDto registerUser (@RequestBody UserRegisterDto userRegisterDto) {
		// insert user into database
		TokenDto token = userService.saveUser(userRegisterDto);
		return token;
	}
	
	// Get connected/created user
	@Operation (summary = "Connected User", description = "Get the connected user information")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "User information retrieved successfully",
		                content = @Content(schema = @Schema(implementation = UserCurrentDto.class))),
		    @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid or missing token",
		                content = @Content(schema = @Schema(implementation = ApiErrorDto.class))),
		    @ApiResponse(responseCode = "404", description = "User not found",
		                content = @Content(schema = @Schema(implementation = ApiErrorDto.class)))
		})
	@GetMapping("/me")
	public UserCurrentDto getCurrentUser (Principal principal){
		User fetchedUser = userService.findUserByEmail(principal.getName());
		return userMapper.toCurrentUserDto(fetchedUser);
	
	}
	
	// Login user
	@Operation (summary = "Login User", description = "Login user with credentials and return Token")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "User logged in successfully",
	                content = @Content(schema = @Schema(implementation = TokenDto.class))),
	    @ApiResponse(responseCode = "401", description = "Invalid credentials",
	                content = @Content(schema = @Schema(implementation = ApiErrorDto.class)))
	    })
	@PostMapping ("/login")
	public TokenDto loginUser (@RequestBody UserLoginDto userLoginDto) {
		// Login user and get token
		return userService.loginUser(userLoginDto);
	
	}

}
