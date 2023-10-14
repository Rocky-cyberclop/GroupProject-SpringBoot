package com.teenboutique.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teenboutique.web.entities.Product;
import com.teenboutique.web.repositories.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository proRepo;
	
	public List<Product> getTrendyProduct(){
		return proRepo.findTrendyProduct();
	}

}
