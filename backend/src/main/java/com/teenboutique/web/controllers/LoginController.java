package com.teenboutique.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.teenboutique.web.entities.Customer;
import com.teenboutique.web.services.CustomerService;

//This is where I handle the end point of this features
//Controller code need to be short and clean in order to write a short and clean RestController too
@Controller
public class LoginController {
	@Autowired
	private CustomerService cusSer;
	
	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(Model model, @RequestParam("email") String email, @RequestParam("password") String password) {
		Customer c = cusSer.getCusByEmail(email);
		BCryptPasswordEncoder cryptPassword = new BCryptPasswordEncoder();
		if (cryptPassword.matches(password, c.getPassword()))
			return "redirect:/";//return "main";
		model.addAttribute("error", "Bạn đã nhập sai email hoặc mật khẩu");
		return "login";
	}
}
