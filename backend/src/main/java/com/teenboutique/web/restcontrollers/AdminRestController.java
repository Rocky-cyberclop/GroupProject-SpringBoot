package com.teenboutique.web.restcontrollers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teenboutique.web.entities.Employee;
import com.teenboutique.web.services.EmployeeService;
import com.teenboutique.web.services.OrderService;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {
	@Autowired
	private OrderService orSer;
	
	@Autowired
	private EmployeeService employeeService;
	
//	**Statistic part
	@GetMapping("/statistic/all/{start}/{end}")
	public ResponseEntity<String> getAllStatistic(@PathVariable("start") LocalDate start, @PathVariable("end") LocalDate end) {
		return new ResponseEntity<String>(orSer.findOrderInRange(start, end).toString(), HttpStatus.OK);
	}
	
	@GetMapping("/management/employees")
	public ResponseEntity<String> showAllEmployee() {
		return showAllEmployeeOnePage(1);
	}

	@GetMapping("/management/employees/{page}")
	public ResponseEntity<String> showAllEmployeeOnePage(@PathVariable("page") int currentPage) {
		Page<Employee> page = employeeService.findPage(currentPage);
		int totalPages = page.getTotalPages();
		List<Employee> emps = page.getContent();
		return new ResponseEntity<String>(page.toString(), HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
