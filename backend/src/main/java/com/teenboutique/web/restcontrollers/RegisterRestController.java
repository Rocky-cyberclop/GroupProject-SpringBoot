package com.teenboutique.web.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teenboutique.web.dto.CustomerDto;
import com.teenboutique.web.entities.Customer;
import com.teenboutique.web.services.CustomerService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class RegisterRestController {
	 
	@Autowired
	private CustomerService cusSer;

    @PostMapping("/register")
    public String register(@RequestBody @Valid CustomerDto customerDto, BindingResult result) {
        if (result.hasErrors()) {
            return "Validation failed";
        }

        if (!isEmailAvailable(customerDto.getEmail())) {
            return "Email is not available";
        }

        Customer customer = convertDtoToCustomer(customerDto);

        cusSer.registerCustomer(customer);
        return "Registration successful";
    }

    private boolean isEmailAvailable(String email) {
        Customer existingCustomer = cusSer.getCusByEmail(email);
        return existingCustomer == null;
    }

    private Customer convertDtoToCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhone(customerDto.getPhone());
        customer.setAddress(customerDto.getAddress());
        customer.setDob(customerDto.getDob());
        customer.setGender(customerDto.isGender());
        customer.setAvatar(customerDto.getAvatar());
        customer.setLocked(customerDto.isLocked());
        customer.setPassword(customerDto.getPassword());
        return customer;
    }
}
