package com.teenboutique.web.dto;

import com.teenboutique.web.entities.Size;

public class SizeDto {
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

	public SizeDto() {
		super();
	}

	public SizeDto(Size s) {
		super();
		this.id = s.getId();
		this.name = s.getName();
	}

}
