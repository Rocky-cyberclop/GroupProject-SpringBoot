package com.teenboutique.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teenboutique.web.repositories.CustomerRepository;



//This is where I create all the logical action of the Customer
//Service calls function from Repository. There are some funcs that created automatically
@Service
public class CustomerService {
	@Autowired
	private CustomerRepository CusRepo;
}
