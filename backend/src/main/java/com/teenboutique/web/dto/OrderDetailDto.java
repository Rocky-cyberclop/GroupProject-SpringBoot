package com.teenboutique.web.dto;

public class OrderDetailDto {
	private Long product;
	private Long size;
	private int quantity;
	private Long price;
	public Long getProduct() {
		return product;
	}
	public void setProduct(Long product) {
		this.product = product;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public OrderDetailDto() {
		super();
	}
	public OrderDetailDto(Long product, Long size, int quantity, Long price) {
		super();
		this.product = product;
		this.size = size;
		this.quantity = quantity;
		this.price = price;
	}
	
}
