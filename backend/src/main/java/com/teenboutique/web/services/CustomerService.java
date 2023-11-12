package com.teenboutique.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.teenboutique.web.entities.Customer;
import com.teenboutique.web.repositories.CustomerRepository;

//This is where I create all the logical action of the Customer
//Service calls function from Repository. There are some funcs that created automatically
@Service
public class CustomerService {
	@Autowired
	private CustomerRepository cusRepo;
	
	public Customer login(String email, String password) {
		return cusRepo.findByEmailAndPassword(email, password); 
	}
	
	public List<Customer> findAll() {
		return cusRepo.findAll();
	}
	
	public Customer getCusByEmail(String email) {
		return cusRepo.findByEmail(email);
	}
	
	public void save(Customer customer) {
        cusRepo.save(customer);
    }

	public void registerCustomer(Customer customer) {
		String plainTextPassword = customer.getPassword();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(plainTextPassword);
		customer.setPassword(hashedPassword);
		customer.setAvatar("0");
		customer.setLocked(false);
		
		cusRepo.save(customer);
	}
}