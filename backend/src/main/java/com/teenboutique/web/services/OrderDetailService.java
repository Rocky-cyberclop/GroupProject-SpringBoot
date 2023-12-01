package com.teenboutique.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teenboutique.web.repositories.OrderRepository;
import com.teenboutique.web.entities.CartItem;
import com.teenboutique.web.entities.CustomerOrder;
import com.teenboutique.web.entities.CustomerOrderItem;

@Service
public class OrderDetailService {
	@Autowired
	private OrderRepository orRepo;
	
	public List<CustomerOrderItem> findDetail(Long id){
		return orRepo.findById(id).get().getOrder_items();
	}
	
	public int insertItem(List<CartItem> cartItems) {
		CustomerOrder customerOrder = orRepo.customerOrderLast();
		for (CartItem item : cartItems) {
			orRepo.customerOrderItemIntsert(customerOrder.getId(), 1, item.getProduct_detail().getProduct().getPrice(), item.getQuantity(), "", null, item.getProduct_detail().getProduct().getId(), item.getProduct_detail().getSize().getId());
	    }
		return 1;
	}
}
