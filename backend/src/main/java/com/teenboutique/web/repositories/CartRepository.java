package com.teenboutique.web.repositories;

import com.teenboutique.web.dto.CartItemDto;
import com.teenboutique.web.entities.CartItem;
import com.teenboutique.web.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {
	@Query(value = "select * from cart_item where customer_id = ?1", nativeQuery = true)
    List<CartItem> findByCustomer(Long customerId);
	
	@Query(value = "select * from cart_item where customer_id = ?1", nativeQuery = true)
	List<CartItemDto> getCartItemsByCustomerId(Long customerId);
	
	@Transactional
	@Modifying
	@Query(value = "delete from cart_item where customer_id = ?1 and product_id = ?2", nativeQuery = true)
    void deleteCartItemByCustomerAndProduct(Long customerId, Long productId);
	
	@Transactional
	@Modifying
	@Query(value = "delete from cart_item where customer_id = ?1", nativeQuery = true)
    void deleteAllCartItemByCustomer(Long customerId);
}
   