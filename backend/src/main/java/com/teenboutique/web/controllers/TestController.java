package com.teenboutique.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.teenboutique.web.services.ProductService;

@Controller
public class TestController {
	@Autowired
	private ProductService proSer;
	
	@GetMapping("/hi")
	public String test() {
		proSer.getAll();
		return "/checkout";
	}
}
