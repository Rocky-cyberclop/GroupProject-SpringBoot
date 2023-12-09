package com.teenboutique.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/main/")
public class TestController {
	
	@GetMapping("/hi")
	public String cart() {
		return "/home";
	}
	
	@GetMapping("/signin")
	public String loginForm() {
		return "/login";
	}
}
