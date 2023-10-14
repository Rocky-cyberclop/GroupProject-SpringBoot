package com.teenboutique.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.teenboutique.web.entities.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query(value = "select * from product where length(name) < 40 limit 9;", nativeQuery=true)
	List<Product> findTrendyProduct();

}
