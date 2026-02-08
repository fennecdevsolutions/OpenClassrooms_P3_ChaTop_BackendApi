package com.oc.chatopapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.oc.chatopapi.dto.TokenDto;
import com.oc.chatopapi.dto.UserLoginDto;
import com.oc.chatopapi.dto.UserRegisterDto;
import com.oc.chatopapi.exception.InvalidCredentialsException;
import com.oc.chatopapi.exception.UserAlreadyExistsException;
import com.oc.chatopapi.exception.NotFoundException;
import com.oc.chatopapi.mapper.UserMapper;
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
	
	@Autowired
	private UserMapper userMapper;
	
	
	// Insert new user in database if email does not exist
	public TokenDto saveUser (UserRegisterDto userDto) {
		// Check if user already exists
		if(userRepo.existsByEmail(userDto.getEmail())) {
			throw new UserAlreadyExistsException(
					"User with email " + userDto.getEmail() + " already exists!");		
		}
		//encode password before then store user in DB
		String encodedPw = pwEncoder.encode(userDto.getPassword());
		User user = userMapper.toEntity(userDto);
		user.setPassword(encodedPw);
		userRepo.save(user);
		// generate token and return it
		String token = jwtService.generateToken(user.getEmail());
		return new TokenDto (token);	
	}
	
	// fetch user by email 
	public User findUserByEmail(String email) {
		
		return userRepo.findByEmail(email)
				.orElseThrow(()-> new NotFoundException("User with email " + email + " not found"));
	}
	
	// fetch user by Id 
		public User findUserById(Integer id) {
			
			return userRepo.findById(id)
					.orElseThrow(()-> new NotFoundException("User not found"));
		}
	
	
	// Login user and return JWT token if password match
	public TokenDto loginUser (UserLoginDto userLoginDto) {
		User registeredUser = userRepo.findByEmail(userLoginDto.getEmail())
				.orElseThrow(()-> new InvalidCredentialsException("Invalid email or password"));
	// verify password
		if(!pwEncoder.matches(userLoginDto.getPassword(),registeredUser.getPassword())) {
			throw new InvalidCredentialsException("Invalid email or password");
		}
		
		String token = jwtService.generateToken(registeredUser.getEmail());
		return new TokenDto (token);
		
	}
	

}
