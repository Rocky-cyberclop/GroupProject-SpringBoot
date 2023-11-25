package com.teenboutique.web.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.teenboutique.web.repositories.OrderRepository;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.teenboutique.web.dto.StatisticDto;
import com.teenboutique.web.entities.CustomerOrder;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orRepo;
	
	@Autowired
	private EmployeeService empSer;
	
	public Page<CustomerOrder> findPage(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber - 1, 10);
		return orRepo.findAllOrderUnexamined(pageable);
	}
	
	public CustomerOrder take(Long id) {
		CustomerOrder order = orRepo.findById(id).get();
		order.setStatus("Done");
		order.setEmployee(empSer.getEmpById(98047L));
		return orRepo.save(order);
	}
	
	public JSONObject findOrderInRange(LocalDate start, LocalDate end){
		List<StatisticDto> orList = orRepo.findAllOrderWithDate(start.toString(), end.toString());
		JSONObject res = new JSONObject();
		List<String> labels = new ArrayList<String>();
		List<Long> values = new ArrayList<Long>();
		for(StatisticDto or : orList) {
			labels.add(or.getDate().toString());
			values.add(or.getSellPerDay());
		}
		res.put("labels", labels);
		res.put("values", values);
		return res;
	}
	
	public CustomerOrder save(CustomerOrder customerOrder) {
		return orRepo.save(customerOrder);
	}
	
	public CustomerOrder lastItem() {
		return orRepo.customerOrderLast();
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
