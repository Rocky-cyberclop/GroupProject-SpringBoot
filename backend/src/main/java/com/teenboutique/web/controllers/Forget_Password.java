package com.teenboutique.web.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.teenboutique.web.entities.Customer;
import com.teenboutique.web.services.CustomerService;

@Controller
public class Forget_Password {
	@Autowired
	private CustomerService cusSer;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@GetMapping("/forget")
	public String forgetPasswordForm(Model model) {
	    model.addAttribute("customer", new Customer());
		return "forget";
	}
	
	public static String generateRandomPassword() {
	    return UUID.randomUUID().toString().substring(0, 10);
	}
	
    @PostMapping("/forget")
    public String processForgetPasswordForm(@ModelAttribute("customer") Customer customer, RedirectAttributes redirectAttributes) {
        Customer existingCustomer = cusSer.getCusByEmail(customer.getEmail());

        if (existingCustomer != null) {
            // Generate a random password
            String newPassword = generateRandomPassword();

            // Hash the generated password
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(newPassword);

            // Update customer's password in the database
            existingCustomer.setPassword(hashedPassword);
            cusSer.save(existingCustomer);

            // Send an email with the new password
            sendPasswordEmail(existingCustomer.getEmail(), newPassword);

            // Add a success message for the user
            redirectAttributes.addFlashAttribute("successMessage", "Một Mail Chứa Mật Khẩu Mới Của Bạn Đã Được Gửi.");
        } else {
            // Add an error message for the user
            redirectAttributes.addFlashAttribute("errorMessage", "Email Không Tồn Tại Với Tài Khoản Nào.");
        }

        return "redirect:/forget";
    }

    private void sendPasswordEmail(String to, String newPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Đặt Lại Mật Khẩu");
        message.setText("Mật Khẩu Mới Của Bạn Là: " + newPassword);
        
        // Send the email
        javaMailSender.send(message);
    }
}

