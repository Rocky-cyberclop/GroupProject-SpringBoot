package com.teenboutique.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.teenboutique.web.entities.CartItem;
import com.teenboutique.web.entities.ProductDetail;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	@Query(value = "SELECT * FROM cart_item WHERE customer_id = ?1 and product_id = ?2 and size_id = ?3" , nativeQuery=true)
	CartItem findByIdAndProductAndSize(Long id, Long product_id, Long size_id); 
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO cart_item(customer_id,quantity,product_id,size_id) VALUES (?1,?2,?3,?4) ", nativeQuery = true)
	void createCartItem(Long customer_id, int quantity, Long product_id, Long size_id);
}

