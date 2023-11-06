package com.teenboutique.web.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 2000)
	private String name;
	private boolean stop_sale;
	@Column(columnDefinition="LONGTEXT")
	private String description;
	private long price;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product", orphanRemoval = true)
	private List<ProductImage> images = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product", orphanRemoval = true)
	private List<ProductDetail> product_details = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isStop_sale() {
		return stop_sale;
	}

	public void setStop_sale(boolean stop_sale) {
		this.stop_sale = stop_sale;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<ProductImage> getImages() {
		return images;
	}

	public void setImages(List<ProductImage> images) {
		this.images = images;
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
