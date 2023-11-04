package com.teenboutique.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.teenboutique.web.services.CustomerService;

@Controller
public class RegisterController {
	@Autowired
	private CustomerService cusSer;
	
	@GetMapping("/register")
	public String registerForm() {
		return "register";
	}
}
