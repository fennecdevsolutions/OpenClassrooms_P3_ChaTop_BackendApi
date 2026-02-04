package com.oc.ChatopApi.service;


import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oc.ChatopApi.configuration.CustomProperties;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	
	@Autowired
	private CustomProperties customProps;
	
	// moved getting the secret key to function in order to be sure that CustomProperties is injected.
	private SecretKey getSigningKey() {
		return Keys.hmacShaKeyFor(customProps.getJWTSecret().getBytes());
	}
	
	
	public String generateToken (String email) {
		return Jwts.builder().setSubject(email)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 *60 * 60 * 12))
				.signWith(getSigningKey())
				.compact();
	}
	
	// extracting the "sub" using the token (sub = email)
	public String extractUsername(String token) {
	    return Jwts.parserBuilder()
	            .setSigningKey(getSigningKey())
	            .build()
	            .parseClaimsJws(token)
	            .getBody()
	            .getSubject();
	}
	
}
