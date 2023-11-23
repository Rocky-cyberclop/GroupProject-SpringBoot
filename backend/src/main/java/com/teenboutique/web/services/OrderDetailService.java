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
//	        CustomerOrderItem orderItem = new CustomerOrderItem();
//	        orderItem.setCustomer_order(customerOrder);
//	        orderItem.setId(item.getId().getProduct_detail_id().getProduct_id(), item.getId().getProduct_detail_id().getSize_id());
//	        orderItem.setPoint(1);
//	        orderItem.setPrice(productService.findById(item.getId().getProduct_detail_id().getProduct_id()).getPrice()); 
//	        orderItem.setQuantity(item.getQuantity()); 
//	        orderItem.setRate_content("");
//	        orderItem.setRate_date(null);
//	        orderItem.setProduct_detail(item.getProduct_detail());
//	        customerOrderItems.add(orderItem);
	        
//		System.out.println(orderItem.getCustomer_order().getId());

//			System.out.println(item.getQuantity());
//			System.out.println(item.getProduct_detail().getSize().getId());
	    }
		return 1;
	}
}
