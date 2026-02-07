package com.oc.chatopapi.service;


import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.oc.chatopapi.exception.InvalidCredentialsException;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	
	@Value("${JWTSecret}")
	private String JWTsecret;
	
	
	// moved getting the secret key to function in order to be sure that CustomProperties is injected.
	private SecretKey getSigningKey() {
		return Keys.hmacShaKeyFor(JWTsecret.getBytes());
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
		try {
	    return Jwts.parserBuilder()
	            .setSigningKey(getSigningKey())
	            .build()
	            .parseClaimsJws(token)
	            .getBody()
	            .getSubject();
		} catch (ExpiredJwtException ex) {
	        throw new InvalidCredentialsException("JWT token expired");
	    } catch (JwtException | IllegalArgumentException ex) {
	        throw new InvalidCredentialsException("Invalid JWT token");
	    }
	}
	}
	

