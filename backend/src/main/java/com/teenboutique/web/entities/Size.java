package com.teenboutique.web.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Size")
public class Size {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "size", orphanRemoval = true)
	private List<ProductDetail> product_details = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProductDetail> getProduct_details() {
		return product_details;
	}

	public void setProduct_details(List<ProductDetail> product_details) {
		this.product_details = product_details;
	}

	public Long getId() {
		return id;
	}
	
	
}
