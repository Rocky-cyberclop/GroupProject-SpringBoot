package com.teenboutique.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.teenboutique.web.entities.Customer;
import com.teenboutique.web.services.CustomerService;

import jakarta.validation.Valid;


@Controller
public class RegisterController {
	@Autowired
	private CustomerService cusSer;
	
	@GetMapping("/register")
	public String registerForm(Model model) {
	    model.addAttribute("customer", new Customer());
	    return "register";
	}
	
	private boolean isEmailAvailable(String email) {
        Customer existingCustomer = cusSer.getCusByEmail(email);
        return existingCustomer == null;
    }
    
	@PostMapping("/register")
	public String register(@ModelAttribute @Valid Customer customer, BindingResult result) {
		if (result.hasErrors()) {
		        return "register"; 
		}
		if (!isEmailAvailable(customer.getEmail())) {
	        result.rejectValue("email", "error.email", "Email Đã Có Người Sử Dụng");
	        return "register";
	    }
		cusSer.registerCustomer(customer);
	    return "redirect:/login";
	}


}