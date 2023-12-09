package com.teenboutique.web.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.teenboutique.web.entities.Employee;
import com.teenboutique.web.repositories.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository empRepo;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private LoginEntityDetailServiceImpl loginEntityDetailServiceImpl;
	
	public Employee login(Long id, String pass) {
		return empRepo.findByIdAndPassword(id, pass);
	}
	
	public String loginEncr(Long id, String pass) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		try {
			Employee employee = this.getEmpById(id);
			if(bCryptPasswordEncoder.matches(pass, employee.getPassword())) {
				String jwt = jwtService.generateToken(new User(id.toString(), pass, LoginEntityDetailServiceImpl.getEmployeeAuthorities(employee)));
				return jwt;
			}
		}
		catch (NoSuchElementException e) {
			// TODO: handle exception
			return "Not found";
		}
		catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return "Wrong pass";

	}

	public List<Employee> findAll() {
		return empRepo.findAll();
	}
	
	public Page<Employee> findPage(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber - 1, 5);
		return empRepo.findAll(pageable);
	}

	public Employee getEmpById(Long id) {
		return empRepo.findById(id).get();
	}
	
	public Employee save(Employee e) {
		return empRepo.save(e);
	}
}
