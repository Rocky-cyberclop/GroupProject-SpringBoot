package com.teenboutique.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.teenboutique.web.entities.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	@Query(value = "SELECT * FROM cart_item WHERE customer_id = ?1 and product_id = ?2 and size_id = ?3" , nativeQuery=true)
	CartItem findByIdAndProductAndSize(Long id, Long product_id, Long size_id); 
}
