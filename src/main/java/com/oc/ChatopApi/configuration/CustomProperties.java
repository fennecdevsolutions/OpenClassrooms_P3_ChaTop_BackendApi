package com.oc.ChatopApi.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix="com.oc.chatopapi")
public class CustomProperties {
	private String JWTSecret;
	

}
