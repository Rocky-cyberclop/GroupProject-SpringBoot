package com.teenboutique.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("main");
	}

	@Bean
  public WebMvcConfigurer corsConfigurer() {
	  return new WebMvcConfigurer() {
		  @Override
		  public void addCorsMappings(CorsRegistry registry) {
			  registry.addMapping("/**").allowedOrigins("http://localhost:3000")
			  .allowedMethods("PUT", "POST", "DELETE", "GET").allowCredentials(true);
		  }
	};
  }

}

