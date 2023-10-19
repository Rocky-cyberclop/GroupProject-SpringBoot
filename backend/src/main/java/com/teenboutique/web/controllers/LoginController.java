package com.teenboutique.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.teenboutique.web.services.CustomerService;

//This is where I handle the end point of this features
//Controller code need to be short and clean in order to write a short and clean RestController too
@Controller
public class LoginController {
	@Autowired
	private CustomerService CusSer;
	
}
