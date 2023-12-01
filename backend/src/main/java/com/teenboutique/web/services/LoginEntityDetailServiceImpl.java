package com.teenboutique.web.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.teenboutique.web.entities.Customer;
import com.teenboutique.web.entities.Employee;
import com.teenboutique.web.repositories.CustomerRepository;
import com.teenboutique.web.repositories.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class LoginEntityDetailServiceImpl implements UserDetailsService {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Long empLong = Long.parseLong(username);
			Employee employee = employeeRepository.findById(empLong)
					.orElseThrow(() -> new UsernameNotFoundException("Employee id " + empLong + " not found!"));
			return new User(employee.getId().toString(), employee.getPassword(), getEmployeeAuthorities(employee));
		} catch (NumberFormatException e) {
			Customer customer = customerRepository.findByEmailOpt(username)
					.orElseThrow(() -> new UsernameNotFoundException("Customer email " + username + "not found"));
			return new User(customer.getEmail(), customer.getPassword(), getUserAuthorities());
		}
	}

	private static Collection<? extends GrantedAuthority> getUserAuthorities() {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("User"));
		return authorities;
	}

	private static Collection<? extends GrantedAuthority> getEmployeeAuthorities(Employee e) {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(e.getRole().getName()));
		return authorities;
	}

}
