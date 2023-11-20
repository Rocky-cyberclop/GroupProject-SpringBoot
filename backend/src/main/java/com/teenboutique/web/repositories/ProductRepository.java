package com.teenboutique.web.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.teenboutique.web.entities.Product;

public interface ProductRepository  extends JpaRepository<Product, Long> {
	@Query(value = "SELECT * FROM product WHERE stop_sale = false", nativeQuery=true)
	Page<Product> findAllProductStillSell(Pageable page);
}
