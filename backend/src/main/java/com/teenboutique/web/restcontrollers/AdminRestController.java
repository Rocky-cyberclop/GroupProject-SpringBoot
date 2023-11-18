package com.teenboutique.web.restcontrollers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teenboutique.web.services.OrderService;

@RestController
@RequestMapping("/api/admin/statistic")
public class AdminRestController {
	@Autowired
	private OrderService orSer;
	
//	**Statistic part
	@GetMapping("/all/{start}/{end}")
	public ResponseEntity<String> getAllStatistic(@PathVariable("start") LocalDate start, @PathVariable("end") LocalDate end) {
		
		return new ResponseEntity<String>(orSer.findOrderInRange(start, end).toString(), HttpStatus.OK);
	}
}
