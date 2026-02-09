package com.oc.chatopapi.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Use an absolute path outside of src/main/resources
        String uploadPath = Paths.get("uploads/images").toAbsolutePath().toUri().toString();

        registry.addResourceHandler("/images/**")
                .addResourceLocations(uploadPath);
    }
}