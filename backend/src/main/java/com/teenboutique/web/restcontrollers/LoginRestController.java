package com.teenboutique.web.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teenboutique.web.dto.AuthResponseAdminDto;
import com.teenboutique.web.dto.CustomerDto;
import com.teenboutique.web.entities.Customer;
import com.teenboutique.web.services.CustomerService;
import com.teenboutique.web.services.JwtService;
import com.teenboutique.web.services.LoginEntityDetailServiceImpl;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class LoginRestController {

    @Autowired
    private CustomerService cusSer;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CustomerLoginRequest loginRequest) {
        Customer c = cusSer.getCusByEmail(loginRequest.getEmail());
        BCryptPasswordEncoder cryptPassword = new BCryptPasswordEncoder();
        
        JwtService jwtService = new JwtService();

        if (c != null && cryptPassword.matches(loginRequest.getPassword(), c.getPassword())) {
        	String jwt = jwtService.generateToken(new User(loginRequest.getEmail(), loginRequest.getPassword(), LoginEntityDetailServiceImpl.getUserAuthorities()));
        	AuthResponseAdminDto authResponseAdminDto = new AuthResponseAdminDto();
        	authResponseAdminDto.setJwt(jwt);
            return ResponseEntity.ok(authResponseAdminDto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

    static class CustomerLoginRequest {
        private String email;
        private String password;


        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
    
    private CustomerDto ConvertCustomerToDto(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress(),
                customer.getDob(),
                customer.isGender(),
                customer.getAvatar(),
                customer.isLocked()
        );
    }
}
