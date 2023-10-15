package com.teenboutique.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.teenboutique.web.entities.ProductDetail;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
	@Query(value = "SELECT * FROM product_detail WHERE product_id = ?1 and size_id = ?2", nativeQuery=true)
	ProductDetail findByProductAndSize(Long id, Long size_id); 
}
