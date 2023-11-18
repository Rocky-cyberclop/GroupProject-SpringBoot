package com.teenboutique.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teenboutique.web.entities.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

}
