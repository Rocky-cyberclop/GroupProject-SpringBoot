package com.teenboutique.web.restcontrollers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teenboutique.web.dto.CustomerDto;
import com.teenboutique.web.entities.Customer;
import com.teenboutique.web.services.CustomerService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ForgetPassRestController {
	
	@Autowired
    private JavaMailSender javaMailSender;

	@Autowired
	private CustomerService cusSer;

	@PostMapping("/forget")
	public ResponseEntity<String> processForgetPasswordForm(@RequestBody CustomerDto customerDto) {
		String email = customerDto.getEmail();

		if (email != null && !email.isEmpty()) {
			Customer existingCustomer = cusSer.getCusByEmail(email);

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

				// Return a success message
				return new ResponseEntity<>("Một Mail Chứa Mật Khẩu Mới Của Bạn Đã Được Gửi.", HttpStatus.OK);
			} else {
				// Return an error message
				return new ResponseEntity<>("Email Không Tồn Tại Với Tài Khoản Nào.", HttpStatus.BAD_REQUEST);
			}
		} else {
			// Return an error message
			return new ResponseEntity<>("Email Không Hợp Lệ.", HttpStatus.BAD_REQUEST);
		}
	}

	private void sendPasswordEmail(String to, String newPassword) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject("Đặt Lại Mật Khẩu");
		message.setText("Mật Khẩu Mới Của Bạn Là: " + newPassword);

		// Send the email
		javaMailSender.send(message);
	}

	private static String generateRandomPassword() {
		return UUID.randomUUID().toString().substring(0, 10);
	}
}