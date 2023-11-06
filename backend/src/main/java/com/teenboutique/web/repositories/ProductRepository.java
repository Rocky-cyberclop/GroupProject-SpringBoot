package com.teenboutique.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teenboutique.web.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
