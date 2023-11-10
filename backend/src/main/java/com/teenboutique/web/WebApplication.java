package com.teenboutique.web;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootApplication
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
		System.out.println("Done!");
	}
	
	@Bean
	    public JavaMailSender javaMailSender() {
	        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	        mailSender.setHost("smtp.gmail.com");
	        mailSender.setPort(587);

	        mailSender.setUsername("anb2014637@student.ctu.edu.vn");
	        mailSender.setPassword("NYS3Lv@C");

	        Properties props = mailSender.getJavaMailProperties();
	        props.put("mail.transport.protocol", "smtp");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");

	        return mailSender;
    }

}
