package com.teenboutique.web.restcontrollers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teenboutique.web.dto.OrderDetailAccountDto;
import com.teenboutique.web.entities.Customer;
import com.teenboutique.web.entities.CustomerOrder;
import com.teenboutique.web.entities.CustomerOrderItem;
import com.teenboutique.web.services.CustomerService;
import com.teenboutique.web.services.OrderService;

@RestController
@RequestMapping("/api/account")
public class AccountRestController {
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping
	public ResponseEntity<Customer> getInfor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Customer customer = customerService.getCusByEmail(authentication.getName());
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Customer> update(@RequestBody Customer customer){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Customer existedCustomer = customerService.getCusByEmail(authentication.getName());
		existedCustomer.setName(customer.getName());
		existedCustomer.setPhone(customer.getPhone());
		existedCustomer.setAddress(customer.getAddress());
		existedCustomer.setDob(customer.getDob());
		existedCustomer.setGender(customer.isGender());
		customerService.save(existedCustomer);
		return new ResponseEntity<Customer>(new Customer(),HttpStatus.OK);
	}
	
	@GetMapping("/orders")
	public ResponseEntity<List<CustomerOrder>> getOrders(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Customer existedCustomer = customerService.getCusByEmail(authentication.getName());
		return new ResponseEntity<List<CustomerOrder>>(existedCustomer.getOrders(),HttpStatus.OK);
	}
	
	@GetMapping("/order/{id}")
	public ResponseEntity<List<OrderDetailAccountDto>> getOrder(@PathVariable("id") Long id){
		List<OrderDetailAccountDto> orderDetailAccountDtos = new ArrayList<OrderDetailAccountDto>();
		for(CustomerOrderItem customerOrderItem :orderService.findById(id).getOrder_items()) {
			OrderDetailAccountDto orderDetailAccountDto = new OrderDetailAccountDto(customerOrderItem);
			orderDetailAccountDtos.add(orderDetailAccountDto);
		}
		return new ResponseEntity<List<OrderDetailAccountDto>>(orderDetailAccountDtos,HttpStatus.OK);
	}
	
	
}
