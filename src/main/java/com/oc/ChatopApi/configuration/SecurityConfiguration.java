package com.oc.chatopapi.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	private JwtFilter jwtFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		// Disable Cross-Site Request Forgery protection ==> de-acivation required for STATELESS sessions
		.csrf(csrf -> csrf.disable()) 
		// Set session policy to STATELESS (no session creation) ==> required for JWT authentication
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		// manage Http requests authorization
		.authorizeHttpRequests(auth -> auth
				// register and login are allowed without token
				.requestMatchers("/api/auth/register","/api/auth/login").permitAll()
				// all other requests require authentication token
				.anyRequest().authenticated()
	            	);
		
		// Adds the JWT filter before the standard user name password filter
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	    return http.build();
	    
	}

    @Bean
    PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

	
	
}

