package com.teenboutique.web.dto;

import com.teenboutique.web.entities.Category;

public class CategoryDto {
	private Long id;

	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CategoryDto() {
		super();
	}

	public CategoryDto(Category c) {
		this.id = c.getId();
		this.name = c.getName();
	}
}
