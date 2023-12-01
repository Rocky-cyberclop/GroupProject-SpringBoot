package com.teenboutique.web.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teenboutique.web.dto.CategoryDto;
import com.teenboutique.web.entities.Category;
import com.teenboutique.web.repositories.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository cateRepo;
	
	public Category add(Category c) {
		return cateRepo.save(c);
	}
	
	public List<Category> findAll() {
		return cateRepo.findAll();
	}
	
	public List<CategoryDto> findAllDto() {
		List<CategoryDto> resCategoryDtos= new ArrayList<CategoryDto>();
		for(Category category : cateRepo.findAll()) {
			CategoryDto categoryDto = new CategoryDto(category);
			resCategoryDtos.add(categoryDto);
		}
		return resCategoryDtos;
	}
	
	public Category findById(Long id) {
		return cateRepo.findById(id).get();
	}
}
