package com.teenboutique.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teenboutique.web.repositories.OrderRepository;
import com.teenboutique.web.entities.CustomerOrderItem;

@Service
public class OrderDetailService {
	@Autowired
	private OrderRepository orRepo;
	
	public List<CustomerOrderItem> findDetail(Long id){
		return orRepo.findById(id).get().getOrder_items();
	}
}
