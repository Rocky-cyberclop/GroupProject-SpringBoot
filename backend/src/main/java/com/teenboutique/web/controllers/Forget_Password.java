package com.teenboutique.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.teenboutique.web.entities.Customer;
import com.teenboutique.web.services.CustomerService;

@Controller
public class Forget_Password {
	@Autowired
	private CustomerService cusSer;
	
	@GetMapping("/forget")
	public String forgetPasswordForm(Model model) {
	    model.addAttribute("customer", new Customer());
		return "forget";
	}
}
