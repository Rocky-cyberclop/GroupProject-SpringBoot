package com.teenboutique.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teenboutique.web.entities.Category;
import com.teenboutique.web.repositories.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository cateRepo;
	
	public List<Category> findAllCategory(){
		return cateRepo.findAll();
	}
	
	

}
