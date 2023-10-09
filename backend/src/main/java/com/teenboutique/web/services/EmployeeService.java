package com.teenboutique.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.teenboutique.web.entities.Employee;
import com.teenboutique.web.repositories.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository empRepo;
	
	public Employee login(Long id, String pass) {
		return empRepo.findByIdAndPassword(id, pass);
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
